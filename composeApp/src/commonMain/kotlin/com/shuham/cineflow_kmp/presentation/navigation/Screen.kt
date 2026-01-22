package com.shuham.cineflow_kmp.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object Home : Screen

    @Serializable
    data class Detail(val movieId: Int) : Screen
}