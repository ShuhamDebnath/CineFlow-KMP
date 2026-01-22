package com.shuham.cineflow_kmp.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

/**
 * Initializes Koin dependency injection.
 * @param config Optional configuration to add platform-specific modules (like Database).
 */
fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(appModule())
    }
}