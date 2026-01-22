package com.shuham.cineflow_kmp.data.remote.service


import com.shuham.cineflow_kmp.data.remote.dto.MoviesResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

interface TmdbApiService {
    suspend fun getPopularMovies(page: Int = 1): MoviesResponseDto
    suspend fun getTopRatedMovies(page: Int = 1): MoviesResponseDto
    suspend fun searchMovies(query: String, page: Int = 1): MoviesResponseDto
}

class TmdbApiServiceImpl(
    private val client: HttpClient
) : TmdbApiService {

    override suspend fun getPopularMovies(page: Int): MoviesResponseDto {
        // Base URL & API Key are handled by NetworkModule's defaultRequest
        return client.get("movie/popular") {
            parameter("page", page)
        }.body()
    }

    override suspend fun getTopRatedMovies(page: Int): MoviesResponseDto {
        return client.get("movie/top_rated") {
            parameter("page", page)
        }.body()
    }

    override suspend fun searchMovies(query: String, page: Int): MoviesResponseDto {
        return client.get("search/movie") {
            parameter("query", query)
            parameter("page", page)
        }.body()
    }
}