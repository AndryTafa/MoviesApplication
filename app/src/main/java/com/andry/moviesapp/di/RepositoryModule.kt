package com.andry.moviesapp.di

import com.andry.moviesapp.ui.repository.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        MoviesRepository(get(), get())
    }
}