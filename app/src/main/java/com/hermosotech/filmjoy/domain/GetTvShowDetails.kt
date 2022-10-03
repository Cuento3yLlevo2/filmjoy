package com.hermosotech.filmjoy.domain

import com.hermosotech.filmjoy.data.TvShowRepository
import com.hermosotech.filmjoy.domain.model.TvShow
import javax.inject.Inject

class GetTvShowDetails @Inject constructor(private val repository: TvShowRepository) {

    suspend operator fun invoke(index: Int): TvShow? {
        val tvShows = repository.getPopularTvShowsFromDatabase()

        if (!tvShows.isNullOrEmpty()) {
            if (index in tvShows.indices)
                return tvShows[index]
        }

        return null
    }

}