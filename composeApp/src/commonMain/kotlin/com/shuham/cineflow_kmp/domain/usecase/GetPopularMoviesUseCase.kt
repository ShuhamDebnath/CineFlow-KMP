package com.shuham.cineflow_kmp.domain.usecase

import com.shuham.cineflow_kmp.domain.model.Movie
import com.shuham.cineflow_kmp.domain.repository.MovieRepository
import com.shuham.cineflow_kmp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetPopularMoviesUseCase(
    private val repository: MovieRepository
) {
    /**
     * @param page The page number to fetch.
     * @param forceRefresh If true, ignores cache and fetches from network.
     */
    operator fun invoke(page: Int, forceRefresh: Boolean = false): Flow<NetworkResult<List<Movie>>> {
        return repository.getPopularMovies(page, forceRefresh)
    }
}