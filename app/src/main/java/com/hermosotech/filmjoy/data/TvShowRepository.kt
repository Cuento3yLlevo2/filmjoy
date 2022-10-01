package com.hermosotech.filmjoy.data

import com.hermosotech.filmjoy.data.model.TvShowProvider
import com.hermosotech.filmjoy.data.model.api.PopularTvShowsResponse
import com.hermosotech.filmjoy.data.network.TvShowService

class TvShowRepository {

    private val api = TvShowService()

    suspend fun getPopularTvShowList() : PopularTvShowsResponse {
        val response = api.getPopularTvShowList()
        TvShowProvider.popularTvShowsResponse = response
        return response
    }
}