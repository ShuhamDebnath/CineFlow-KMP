package com.shuham.cineflow_kmp.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.shuham.cineflow_kmp.data.local.AppDatabase
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val platformModule = module {
    single<AppDatabase> {
        // Android specific database builder
        val dbFile = androidContext().getDatabasePath("cineflow.db")

        Room.databaseBuilder<AppDatabase>(
            context = androidContext(),
            name = dbFile.absolutePath
        )
            .setDriver(BundledSQLiteDriver()) // The KMP SQLite Driver
            .fallbackToDestructiveMigration(true) // Clears DB if schema changes (good for dev)
            .build()
    }

    single<HttpClientEngine> { OkHttp.create() }
}