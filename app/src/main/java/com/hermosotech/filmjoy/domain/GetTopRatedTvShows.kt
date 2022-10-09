package com.hermosotech.filmjoy.domain

import android.content.Context
import com.hermosotech.filmjoy.data.TvShowRepository
import com.hermosotech.filmjoy.data.database.entities.toDatabaseTopRated
import com.hermosotech.filmjoy.domain.model.TvShow
import javax.inject.Inject

class GetTopRatedTvShows @Inject constructor(private val repository : TvShowRepository) {

    suspend operator fun invoke(context: Context?, language: String? = null) : List<TvShow>? {
        val tvShowsResponse = context?.let { repository.getTopRatedTvShowsResponseFromApi(it, language) }

        return if (tvShowsResponse?.results?.isNotEmpty() == true){
            repository.clearTopRatedTvShows()
            // Insert TvShows to Room dataBase
            repository.insertTopRatedTvShows(tvShowsResponse.toDatabaseTopRated())
            tvShowsResponse.results
        } else {
            repository.getTopRatedTvShowsFromDatabase()
        }
    }

}