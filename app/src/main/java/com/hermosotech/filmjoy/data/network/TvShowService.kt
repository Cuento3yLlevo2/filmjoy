package com.hermosotech.filmjoy.data.network

import com.hermosotech.filmjoy.core.RetrofitHelper
import com.hermosotech.filmjoy.data.model.api.PopularTvShowsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TvShowService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getPopularTvShowList() : PopularTvShowsResponse {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(TvShowApiClient::class.java).getPopularTvShowList()
            response.body() ?: PopularTvShowsResponse(0, emptyList(), 0, 0)
        }
    }
}