package com.shuham.cineflow_kmp.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.shuham.cineflow_kmp.data.local.dao.MovieDao
import com.shuham.cineflow_kmp.data.local.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1
)
@ConstructedBy(AppDatabaseConstructor::class) // Links to the expect/actual constructor
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}


@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}
