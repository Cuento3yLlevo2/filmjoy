package com.hermosotech.filmjoy.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hermosotech.filmjoy.data.database.entities.GenreEntity
import java.lang.reflect.Type


class Converters {

    @TypeConverter
    fun fromListOfInts(genreIds: List<Int>): String? {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Int>>() {}.type
        return gson.toJson(genreIds, type)
    }

    @TypeConverter
    fun toListOfInts(genreIdsString: String): List<Int>? {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson<List<Int>>(genreIdsString, type)
    }

    @TypeConverter
    fun fromListOfStrings(countryList: List<String>): String? {
        val gson = Gson()
        val type: Type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(countryList, type)
    }

    @TypeConverter
    fun toListOfStrings(countryListString: String): List<String>? {
        val gson = Gson()
        val type: Type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson<List<String>>(countryListString, type)
    }

    @TypeConverter
    fun fromGenres(genresList: List<GenreEntity>): String? {
        val gson = Gson()
        val type: Type = object : TypeToken<List<GenreEntity>>() {}.type
        return gson.toJson(genresList, type)
    }

    @TypeConverter
    fun toGenres(genresString: String): List<GenreEntity>? {
        val gson = Gson()
        val type: Type = object : TypeToken<List<GenreEntity>>() {}.type
        return gson.fromJson<List<GenreEntity>>(genresString, type)
    }
}