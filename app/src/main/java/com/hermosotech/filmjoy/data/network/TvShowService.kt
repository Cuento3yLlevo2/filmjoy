package com.hermosotech.filmjoy.data.network

import com.hermosotech.filmjoy.data.model.api.TvShowsResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TvShowService @Inject constructor(private val api : TvShowApiClient) {

    suspend fun getPopularTvShowList() : TvShowsResponseModel {
        return withContext(Dispatchers.IO) {
            val response = api.getPopularTvShowList()
            response.body() ?: TvShowsResponseModel(0, emptyList(), 0, 0)
        }
    }
}