package com.shuham.cineflow_kmp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.shuham.cineflow_kmp.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDao {
    @Upsert
    suspend fun upsertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movies")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    suspend fun getMovieById(movieId: Int): MovieEntity?

    @Query("DELETE FROM movies")
    suspend fun clearAll()
}