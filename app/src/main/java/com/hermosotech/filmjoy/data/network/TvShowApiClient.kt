package com.hermosotech.filmjoy.data.network

import com.hermosotech.filmjoy.core.di.NetworkModule.API_KEY
import com.hermosotech.filmjoy.data.model.api.ApiConfigModel
import com.hermosotech.filmjoy.data.model.api.GenresReponseModel
import com.hermosotech.filmjoy.data.model.api.TvShowsResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowApiClient {

    @GET("tv/popular")
    suspend fun getPopularTvShowList(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int? = 1,
    ): retrofit2.Response<TvShowsResponseModel>

    @GET("tv/top_rated")
    suspend fun getTopRatedTvShowList(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int? = 1,
    ): retrofit2.Response<TvShowsResponseModel>

    @GET("configuration")
    suspend fun getApiConfig(
        @Query("api_key") apiKey: String = API_KEY,
    ): retrofit2.Response<ApiConfigModel>

    @GET("genre/tv/list")
    suspend fun getGenresTv(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String? = "en-US"
    ): retrofit2.Response<GenresReponseModel>
}