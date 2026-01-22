package com.shuham.cineflow_kmp.utils

import com.shuham.cineflow_kmp.BuildConfig

object Constants {
    const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

    // Now pulling securely from the generated BuildConfig class
    const val TMDB_API_KEY = BuildConfig.TMDB_API_KEY
}