package com.hermosotech.filmjoy.domain

import android.content.Context
import com.hermosotech.filmjoy.data.TvShowRepository
import com.hermosotech.filmjoy.data.database.entities.toDatabase
import com.hermosotech.filmjoy.domain.model.TvShow
import javax.inject.Inject

class GetPopularTvShows @Inject constructor(private val repository : TvShowRepository) {

    suspend operator fun invoke(context: Context?, language: String? = null): List<TvShow>? {
        val tvShowsResponse = context?.let { repository.getPopularTvShowsResponseFromApi(it, language) }

        return if (tvShowsResponse?.results?.isNotEmpty() == true){
            repository.clearPopularTvShows()
            // Insert TvShows to Room dataBase
            repository.insertPopularTvShows(tvShowsResponse.toDatabase())
            tvShowsResponse.results
        } else {
            repository.getPopularTvShowsFromDatabase()
        }
    }

}