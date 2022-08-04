package com.andry.moviesapp.ui.repository

interface Repository {
    suspend fun getMovieResponseFromNetwork()
}