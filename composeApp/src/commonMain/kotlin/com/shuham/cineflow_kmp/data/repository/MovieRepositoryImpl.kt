package com.shuham.cineflow_kmp.data.repository

import com.shuham.cineflow_kmp.data.local.dao.MovieDao
import com.shuham.cineflow_kmp.data.mappers.toDomain
import com.shuham.cineflow_kmp.data.mappers.toEntity
import com.shuham.cineflow_kmp.data.remote.service.TmdbApiService
import com.shuham.cineflow_kmp.domain.model.Movie
import com.shuham.cineflow_kmp.domain.repository.MovieRepository
import com.shuham.cineflow_kmp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow


class MovieRepositoryImpl(
    private val api: TmdbApiService,
    private val dao: MovieDao
) : MovieRepository {

    override fun getPopularMovies(
        page: Int,
        forceFetchFromRemote: Boolean
    ): Flow<NetworkResult<List<Movie>>> = flow {
        emit(NetworkResult.Loading())

        val localMovies = dao.getMovies().first()
        val isLocalEmpty = localMovies.isEmpty()
        val domainMovies = localMovies.map { it.toDomain() }

        if (!isLocalEmpty) {
            emit(NetworkResult.Loading(data = domainMovies))
        }

        val shouldLoadFromCache = !isLocalEmpty && !forceFetchFromRemote
        if (shouldLoadFromCache) {
            emit(NetworkResult.Success(domainMovies))
            return@flow
        }

        try {
            val response = api.getPopularMovies(page)

            if (page == 1) {
                dao.clearAll()
            }
            dao.upsertMovies(response.results.map { it.toEntity() })

            val newLocalMovies = dao.getMovies().first().map { it.toDomain() }
            emit(NetworkResult.Success(newLocalMovies))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(NetworkResult.Error(
                message = "Could not load data: ${e.message}",
                data = domainMovies
            ))
        }
    }

    override suspend fun getMovieById(id: Int): NetworkResult<Movie> {
        return try {
            val localMovie = dao.getMovieById(id)
            if (localMovie != null) {
                NetworkResult.Success(localMovie.toDomain())
            } else {
                NetworkResult.Error("Movie not found in cache")
            }
        } catch (e: Exception) {
            NetworkResult.Error("Error: ${e.message}")
        }
    }
}