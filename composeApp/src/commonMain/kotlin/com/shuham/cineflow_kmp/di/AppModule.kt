package com.shuham.cineflow_kmp.di

import org.koin.dsl.module

// Combines all sub-modules
fun appModule() = module {
    includes(
        databaseModule,
        networkModule,
        platformModule,
        repositoryModule,
        useCaseModule,
        viewModelModule
    )
}