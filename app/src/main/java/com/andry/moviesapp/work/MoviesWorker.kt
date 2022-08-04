package com.andry.moviesapp.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.andry.moviesapp.data.network.MovieApiService
import com.andry.moviesapp.ui.repository.MoviesRepository
import com.andry.moviesapp.util.toMovieEntity
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent.inject

class MoviesWorker(context: Context, parameters: WorkerParameters)
    : CoroutineWorker(context, parameters), KoinComponent {

    private val moviesRepository: MoviesRepository by inject(MoviesRepository::class.java)
    private val movieApiService: MovieApiService by inject(MovieApiService::class.java)

    override suspend fun doWork(): Result =
        try {
            moviesRepository.storeMovieResponseToDb(movieApiService.getMoviesResponse().toMovieEntity())
            Result.success()
        } catch(ex: Throwable) {
            Result.failure()
        }
}