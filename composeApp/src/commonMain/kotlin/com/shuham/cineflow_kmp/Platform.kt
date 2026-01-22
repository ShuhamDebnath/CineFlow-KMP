package com.shuham.cineflow_kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform