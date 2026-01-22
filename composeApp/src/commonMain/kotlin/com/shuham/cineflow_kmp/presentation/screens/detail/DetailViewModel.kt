package com.shuham.cineflow_kmp.presentation.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shuham.cineflow_kmp.domain.model.Movie
import com.shuham.cineflow_kmp.domain.usecase.GetMovieByIdUseCase
import com.shuham.cineflow_kmp.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class DetailUiState(
    val movie: Movie? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class DetailViewModel(
    private val getMovieByIdUseCase: GetMovieByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun getMovie(id: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            when (val result = getMovieByIdUseCase(id)) {
                is NetworkResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        movie = result.data,
                        error = null
                    )
                }
                is NetworkResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
                is NetworkResult.Loading -> {
                    // Not typically used for simple suspend calls, but good practice to handle
                    _uiState.value = _uiState.value.copy(isLoading = true)
                }
            }
        }
    }
}