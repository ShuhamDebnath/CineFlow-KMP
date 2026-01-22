package com.shuham.cineflow_kmp.di

import com.shuham.cineflow_kmp.data.local.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    // Provide the MovieDao by getting the AppDatabase instance
    single { get<AppDatabase>().movieDao() }
}