package com.shuham.cineflow_kmp.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shuham.cineflow_kmp.domain.model.Movie
import com.shuham.cineflow_kmp.domain.usecase.GetPopularMoviesUseCase
import com.shuham.cineflow_kmp.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

data class HomeUiState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class HomeViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadMovies(forceRefresh = false)
    }

    fun loadMovies(forceRefresh: Boolean = false) {
        // Collect the Flow from UseCase
        getPopularMoviesUseCase(page = 1, forceRefresh = forceRefresh)
            .onEach { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = true,
                            // Keep showing old data while loading (Offline-First UX)
                            movies = result.data ?: _uiState.value.movies
                        )
                    }
                    is NetworkResult.Success -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            movies = result.data ?: emptyList(),
                            error = null
                        )
                    }
                    is NetworkResult.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = result.message,
                            // Keep showing cached data if network fails
                            movies = result.data ?: _uiState.value.movies
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }
}