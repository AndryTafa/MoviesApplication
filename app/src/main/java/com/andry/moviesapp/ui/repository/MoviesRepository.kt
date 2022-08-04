package com.andry.moviesapp.ui.repository

import com.andry.moviesapp.data.db.MoviesDao
import com.andry.moviesapp.data.db.MoviesEntity
import com.andry.moviesapp.data.network.MovieApiService
import com.andry.moviesapp.util.toMovieDataList
import com.andry.moviesapp.util.toMovieEntity
import kotlinx.coroutines.flow.flowOf

class MoviesRepository(
    private val movieApiService: MovieApiService,
    private val moviesDao: MoviesDao
) : Repository {
    override suspend fun getMovieResponseFromNetwork() {
        storeMovieResponseToDb(movieApiService.getMoviesResponse().toMovieEntity())
    }

    suspend fun storeMovieResponseToDb(moviesEntity: MoviesEntity) {
        moviesDao.insertMovieEntity(moviesEntity)
    }

    suspend fun getMoviesFromDb() = flowOf(
        moviesDao.getMovieEntity().toMovieDataList()
    )
}