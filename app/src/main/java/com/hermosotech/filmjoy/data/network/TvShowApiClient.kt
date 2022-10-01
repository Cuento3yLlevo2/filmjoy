package com.hermosotech.filmjoy.data.network

import com.hermosotech.filmjoy.data.model.api.PopularTvShowsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

interface TvShowApiClient {

    @GET("tv/popular")
    suspend fun getPopularTvShowList(
        @Query("api_key") apiKey: String = "c6aeee577586ba38e487b74dfede5deb",
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int? = 1,
    ): retrofit2.Response<PopularTvShowsResponse>
}