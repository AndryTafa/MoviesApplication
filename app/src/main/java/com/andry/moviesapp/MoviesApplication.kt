package com.andry.moviesapp

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.andry.moviesapp.di.databaseModule
import com.andry.moviesapp.di.networkModule
import com.andry.moviesapp.di.repositoryModule
import com.andry.moviesapp.di.viewModelModule
import com.andry.moviesapp.work.MoviesWorker
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import java.util.concurrent.TimeUnit

class MoviesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        initWorkManager()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MoviesApplication)
            modules(listOf(databaseModule, networkModule, repositoryModule, viewModelModule))
        }
    }

    private fun initWorkManager() {
        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "updateMovies",
            ExistingPeriodicWorkPolicy.KEEP,
            PeriodicWorkRequestBuilder<MoviesWorker>(10, TimeUnit.MINUTES).build()
        )
    }
}