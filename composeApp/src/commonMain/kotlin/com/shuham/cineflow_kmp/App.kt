package com.shuham.cineflow_kmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.shuham.cineflow_kmp.presentation.navigation.Screen
import com.shuham.cineflow_kmp.presentation.screens.detail.DetailScreen
import com.shuham.cineflow_kmp.presentation.screens.home.HomeScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Screen.Home
        ) {
            // Home Route
            composable<Screen.Home> {
                HomeScreen(
                    onMovieClick = { movieId ->
                        navController.navigate(Screen.Detail(movieId))
                    }
                )
            }

            // Detail Route (Type-Safe Argument Passing)
            composable<Screen.Detail> { backStackEntry ->
                val detail = backStackEntry.toRoute<Screen.Detail>()
                DetailScreen(
                    movieId = detail.movieId,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}