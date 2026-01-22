package com.shuham.cineflow_kmp.domain.repository

import com.shuham.cineflow_kmp.domain.model.Movie
import com.shuham.cineflow_kmp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(page: Int, forceFetchFromRemote: Boolean): Flow<NetworkResult<List<Movie>>>
    suspend fun getMovieById(id: Int): NetworkResult<Movie>
}