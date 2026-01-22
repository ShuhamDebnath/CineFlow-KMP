package com.shuham.cineflow_kmp.di

import com.shuham.cineflow_kmp.presentation.screens.detail.DetailViewModel
import com.shuham.cineflow_kmp.presentation.screens.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
}