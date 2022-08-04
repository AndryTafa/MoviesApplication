package com.andry.moviesapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieEntity(moviesEntity: MoviesEntity)

    @Query("SELECT * FROM MoviesEntity")
    suspend fun getMovieEntity() : MoviesEntity
}