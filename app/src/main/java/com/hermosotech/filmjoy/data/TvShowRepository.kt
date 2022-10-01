package com.hermosotech.filmjoy.data

import com.hermosotech.filmjoy.data.model.TvShowProvider
import com.hermosotech.filmjoy.data.model.api.PopularTvShowsResponse
import com.hermosotech.filmjoy.data.network.TvShowService
import javax.inject.Inject

class TvShowRepository @Inject constructor(
    private val api : TvShowService,
    private val tvShowProvider : TvShowProvider
    ) {

    suspend fun getPopularTvShowList() : PopularTvShowsResponse {
        val response = api.getPopularTvShowList()
        tvShowProvider.response = response
        return response
    }
}