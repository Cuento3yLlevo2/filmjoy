package com.hermosotech.filmjoy.domain

import com.hermosotech.filmjoy.data.TvShowRepository
import com.hermosotech.filmjoy.data.model.api.PopularTvShowsResponse

class GetPopularTvShows {

    private val repository = TvShowRepository()

    suspend operator fun invoke() : PopularTvShowsResponse? = repository.getPopularTvShowList()

}