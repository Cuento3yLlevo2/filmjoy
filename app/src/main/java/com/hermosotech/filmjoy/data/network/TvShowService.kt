package com.hermosotech.filmjoy.data.network

import com.hermosotech.filmjoy.data.model.api.ApiConfigModel
import com.hermosotech.filmjoy.data.model.api.GenresReponseModel
import com.hermosotech.filmjoy.data.model.api.TvShowsResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TvShowService @Inject constructor(private val api : TvShowApiClient) {

    suspend fun getPopularTvShowList(language: String? = null): TvShowsResponseModel {
        return withContext(Dispatchers.IO) {
            val response = api.getPopularTvShowList(language = language)
            response.body() ?: TvShowsResponseModel(0, emptyList(), 0, 0)
        }
    }

    suspend fun getTopRatedTvShowList(language: String? = null) : TvShowsResponseModel {
        return withContext(Dispatchers.IO) {
            val response = api.getTopRatedTvShowList(language = language)
            response.body() ?: TvShowsResponseModel(0, emptyList(), 0, 0)
        }
    }

    suspend fun getApiConfig() : ApiConfigModel {
        return withContext(Dispatchers.IO) {
            val response = api.getApiConfig()
            response.body() ?: ApiConfigModel(null, emptyList())
        }
    }

    suspend fun getGenresTv(language: String? = null): GenresReponseModel {
        return withContext(Dispatchers.IO) {
            val response = api.getGenresTv(language = language)
            response.body() ?: GenresReponseModel(emptyList())
        }
    }
}