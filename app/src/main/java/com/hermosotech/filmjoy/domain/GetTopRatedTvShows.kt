package com.hermosotech.filmjoy.domain

import com.hermosotech.filmjoy.data.TvShowRepository
import com.hermosotech.filmjoy.data.database.entities.toDatabaseTopRated
import com.hermosotech.filmjoy.domain.model.TvShow
import javax.inject.Inject

class GetTopRatedTvShows @Inject constructor(private val repository : TvShowRepository) {

    suspend operator fun invoke(language: String? = null) : List<TvShow>? {
        val tvShowsResponse = repository.getTopRatedTvShowsResponseFromApi(language)

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