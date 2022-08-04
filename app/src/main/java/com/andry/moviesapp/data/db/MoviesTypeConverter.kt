package com.andry.moviesapp.data.db

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object MoviesTypeConverter {

    @TypeConverter
    fun movieEntityDataToString(entity: List<MoviesEntityData>) =
        Json.encodeToString(entity)


    @TypeConverter
    fun movieEntityDataFromString(entity: String) : List<MoviesEntityData> =
        Json.decodeFromString(entity)
}