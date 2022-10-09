package com.hermosotech.filmjoy.domain

import android.content.Context
import com.hermosotech.filmjoy.data.TvShowRepository
import com.hermosotech.filmjoy.data.database.entities.toDatabase
import javax.inject.Inject

class GetGenresTv @Inject constructor(private val repository : TvShowRepository) {

    suspend operator fun invoke(context: Context?, language: String? = null) {
        val response = repository.getGenresTvFromApi(context, language)

        if (response?.isNotEmpty() == true){
            repository.clearGenresTv()
            // Insert TvShows to Room dataBase
            repository.insertGenresTv(response.map { it.toDatabase() })
        }
    }

    suspend fun getGenresById(ids: List<Int>): List<String>? {
        var result: MutableList<String>? = null
        repository.getGenresFromDatabase()?.let {
            result = mutableListOf()
            for (genre in it){
                for (id in ids) {
                    if(genre.id == id) result?.add(genre.name)
                }
            }
        }

        return result
    }

}