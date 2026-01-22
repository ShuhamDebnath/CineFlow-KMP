package com.shuham.cineflow_kmp.domain.usecase

import com.shuham.cineflow_kmp.domain.model.Movie
import com.shuham.cineflow_kmp.domain.repository.MovieRepository
import com.shuham.cineflow_kmp.utils.NetworkResult

class GetMovieByIdUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(id: Int): NetworkResult<Movie> {
        return repository.getMovieById(id)
    }
}