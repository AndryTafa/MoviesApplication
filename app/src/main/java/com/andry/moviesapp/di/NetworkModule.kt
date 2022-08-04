package com.andry.moviesapp.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.andry.moviesapp.data.network.MovieApiService
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

@OptIn(ExperimentalSerializationApi::class)
val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://movies-sample.herokuapp.com")
            .client(get())
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(MovieApiService::class.java)
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            )
            .build()
    }
}
