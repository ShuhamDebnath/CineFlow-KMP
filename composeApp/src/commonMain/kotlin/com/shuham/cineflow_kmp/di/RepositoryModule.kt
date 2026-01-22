package com.shuham.cineflow_kmp.di

import com.shuham.cineflow_kmp.data.repository.MovieRepositoryImpl
import com.shuham.cineflow_kmp.domain.repository.MovieRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val repositoryModule = module {
    // Binds MovieRepositoryImpl -> MovieRepository
    singleOf(::MovieRepositoryImpl).bind<MovieRepository>()
}