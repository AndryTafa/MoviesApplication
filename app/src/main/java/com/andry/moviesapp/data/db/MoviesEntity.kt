package com.andry.moviesapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
data class MoviesEntity(
    @PrimaryKey
    val id: Int = 1,
    val data: List<MoviesEntityData>
)

@Serializable
data class MoviesEntityData(
    val genre: String,
    val id: Int,
    val poster: String,
    val title: String,
    val year: String
)