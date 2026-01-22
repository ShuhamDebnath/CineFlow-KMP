package com.shuham.cineflow_kmp

import android.app.Application
import com.shuham.cineflow_kmp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class CineFlowApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize Koin for Android
        initKoin {
            androidLogger()
            androidContext(this@CineFlowApp)
            // We will add the database module here later
        }
    }
}