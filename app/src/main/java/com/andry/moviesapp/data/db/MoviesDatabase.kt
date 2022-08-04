package com.andry.moviesapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(version = 1, entities = [MoviesEntity::class])
@TypeConverters(MoviesTypeConverter::class)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun getMoviesDao() : MoviesDao
}