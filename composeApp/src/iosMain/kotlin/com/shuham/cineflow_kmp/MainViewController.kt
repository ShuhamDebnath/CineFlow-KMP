package com.shuham.cineflow_kmp

import androidx.compose.ui.window.ComposeUIViewController
import com.shuham.cineflow_kmp.di.initKoin

fun MainViewController() = ComposeUIViewController (
    configure = {
        initKoin()
    }
){ App() }