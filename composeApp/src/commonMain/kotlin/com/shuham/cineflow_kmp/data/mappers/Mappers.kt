package com.shuham.cineflow_kmp.data.mappers

import com.shuham.cineflow_kmp.data.local.entity.MovieEntity
import com.shuham.cineflow_kmp.data.remote.dto.MovieDto
import com.shuham.cineflow_kmp.domain.model.Movie
import kotlin.time.Clock

fun MovieDto.toEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        voteCount = voteCount,
        originalLanguage = originalLanguage,
        lastUpdated = Clock.System.now().toEpochMilliseconds()
    )
}

fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        voteCount = voteCount,
        originalLanguage = originalLanguage
    )
}