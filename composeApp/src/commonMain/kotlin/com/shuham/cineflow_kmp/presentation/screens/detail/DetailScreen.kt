package com.shuham.cineflow_kmp.presentation.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cineflowkmp.composeapp.generated.resources.Res
import cineflowkmp.composeapp.generated.resources.arrow_back_24px
import coil3.compose.AsyncImage
import com.shuham.cineflow_kmp.utils.Constants
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun DetailScreen(
    movieId: Int,
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = koinViewModel()
) {
    // Trigger data fetch when screen opens
    LaunchedEffect(movieId) {
        viewModel.getMovie(movieId)
    }

    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(state.movie?.title ?: "Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = vectorResource(Res.drawable.arrow_back_24px),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else if (state.error != null) {
                Text(text = state.error ?: "Unknown error")
            } else {
                state.movie?.let { movie ->
                    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
                        // Backdrop Image
                        movie.backdropPath?.let { path ->
                            AsyncImage(
                                model = "${Constants.IMAGE_BASE_URL}$path",
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(16f / 9f)
                            )
                        }

                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = movie.title,
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Released: ${movie.releaseDate}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "Rating: ⭐ ${movie.voteAverage} (${movie.voteCount} votes)",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = movie.overview,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
        }
    }
}