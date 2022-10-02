package com.hermosotech.filmjoy.domain

import com.hermosotech.filmjoy.data.TvShowRepository
import com.hermosotech.filmjoy.data.database.entities.toDatabase
import com.hermosotech.filmjoy.domain.model.TvShow
import com.hermosotech.filmjoy.domain.model.TvShowsResponse
import javax.inject.Inject

class GetPopularTvShows @Inject constructor(private val repository : TvShowRepository) {

    suspend operator fun invoke() : List<TvShow>? {
        val tvShowsResponse = repository.getPopularTvShowsResponseFromApi()

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