package com.shuham.cineflow_kmp.di

import com.shuham.cineflow_kmp.domain.usecase.GetMovieByIdUseCase
import com.shuham.cineflow_kmp.domain.usecase.GetPopularMoviesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    // We use 'factory' because UseCases are lightweight and usually
    // we want a new instance for every ViewModel that asks for it.
    factoryOf(::GetPopularMoviesUseCase)
    factoryOf(::GetMovieByIdUseCase)
}