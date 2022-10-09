package com.hermosotech.filmjoy.data.network

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import com.hermosotech.filmjoy.data.model.api.ApiConfigModel
import com.hermosotech.filmjoy.data.model.api.GenresReponseModel
import com.hermosotech.filmjoy.data.model.api.TvShowsResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class TvShowService @Inject constructor(private val api : TvShowApiClient) {

    suspend fun getPopularTvShowList(language: String? = null): TvShowsResponseModel? {
        return withContext(Dispatchers.IO) {
            val response = api.getPopularTvShowList(language = language)
            if (response.isSuccessful) response.body() else null
        }
    }

    suspend fun getTopRatedTvShowList(language: String? = null) : TvShowsResponseModel? {
        return withContext(Dispatchers.IO) {
            val response = api.getTopRatedTvShowList(language = language)
            if (response.isSuccessful) response.body() else null
        }
    }

    suspend fun getApiConfig() : ApiConfigModel? {
        return withContext(Dispatchers.IO) {
            val response = api.getApiConfig()
            if (response.isSuccessful) response.body() else null
        }
    }

    suspend fun getGenresTv(language: String? = null): GenresReponseModel? {
        return withContext(Dispatchers.IO) {
            val response = api.getGenresTv(language = language)
            if (response.isSuccessful) response.body() else null
        }
    }
}