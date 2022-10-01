package com.hermosotech.filmjoy.domain

import com.hermosotech.filmjoy.data.TvShowRepository
import com.hermosotech.filmjoy.data.model.api.PopularTvShowsResponse
import javax.inject.Inject

class GetPopularTvShows @Inject constructor(private val repository : TvShowRepository) {

    suspend operator fun invoke() : PopularTvShowsResponse? = repository.getPopularTvShowList()

}