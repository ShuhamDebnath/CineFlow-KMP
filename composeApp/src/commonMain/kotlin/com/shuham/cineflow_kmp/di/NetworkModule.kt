package com.shuham.cineflow_kmp.di

import com.shuham.cineflow_kmp.data.remote.service.TmdbApiService
import com.shuham.cineflow_kmp.data.remote.service.TmdbApiServiceImpl
import com.shuham.cineflow_kmp.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {

    // 1. Provide Ktor HttpClient
    single {
        HttpClient(get<HttpClientEngine>()) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

//            install(Logging) {
//                logger = Logger.SIMPLE
//                level = LogLevel.INFO
//            }

            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "api.themoviedb.org"
                    path("3/")
                    parameters.append("api_key", Constants.TMDB_API_KEY)
                }
                header("Content-Type", "application/json")
            }
        }
    }

    // 2. Provide the API Service
    singleOf(::TmdbApiServiceImpl).bind<TmdbApiService>()

}