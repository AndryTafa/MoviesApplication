package com.andry.moviesapp.util

import com.andry.moviesapp.data.MovieData
import com.andry.moviesapp.data.db.MoviesEntity
import com.andry.moviesapp.data.db.MoviesEntityData
import com.andry.moviesapp.data.network.MoviesResponse
import com.andry.moviesapp.data.network.MoviesResponseData

//Convert from network data to app data
fun List<MoviesResponseData>.toMovieData() = map {
    MovieData(
        genre = it.genre,
        id = it.id,
        poster = it.poster,
        title = it.title,
        year = it.year
    )
}

//Convert from network data to database entity data
fun List<MoviesResponseData>.toMovieEntityData() = map {
    MoviesEntityData(
        genre = it.genre,
        id = it.id,
        poster = it.poster,
        title = it.title,
        year = it.year
    )
}

//Convert from network module to database model
fun MoviesResponse.toMovieEntity() = MoviesEntity(
    data = data.toMovieEntityData()
)

//Convert from database model to app model
fun MoviesEntity.toMovieDataList() = data.map {
    MovieData(
        genre = it.genre,
        id = it.id,
        poster = it.poster,
        title = it.title,
        year = it.year
    )
}

