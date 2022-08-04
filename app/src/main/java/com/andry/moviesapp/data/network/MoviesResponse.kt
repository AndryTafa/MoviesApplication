package com.andry.moviesapp.data.network

import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    val data: List<MoviesResponseData>
)

@Serializable
data class MoviesResponseData(
    val genre: String,
    val id: Int,
    val poster: String,
    val title: String,
    val year: String
)