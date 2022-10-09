package com.hermosotech.filmjoy.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.hermosotech.filmjoy.data.database.dao.TvShowDao
import com.hermosotech.filmjoy.data.database.entities.GenreEntity
import com.hermosotech.filmjoy.data.database.entities.PopularTvShowEntity
import com.hermosotech.filmjoy.data.database.entities.TopRatedTvShowEntity
import com.hermosotech.filmjoy.data.network.TvShowService
import com.hermosotech.filmjoy.domain.model.*
import javax.inject.Inject


/**
 * This repository will have the logic to fetch the network results, manage the offline cache and to keep the database up-to-date.
 */
class TvShowRepository @Inject constructor(
    private val api: TvShowService,
    private val tvShowDao: TvShowDao
    ) {

    private fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val cap = cm.getNetworkCapabilities(cm.activeNetwork) ?: return false
            return cap.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            val networks = cm.allNetworks
            for (n in networks) {
                val nInfo = cm.getNetworkInfo(n)
                if (nInfo != null && nInfo.isConnected) return true
            }
        }

        return false
    }

    // Calls to API

    suspend fun getPopularTvShowsResponseFromApi(context: Context, language: String? = null): TvShowsResponse? {
        val response = if (isNetworkAvailable(context)) api.getPopularTvShowList(language) else null
        return response?.toDomain()
    }

    suspend fun getTopRatedTvShowsResponseFromApi(context: Context, language: String? = null): TvShowsResponse? {
        val response = if (isNetworkAvailable(context)) api.getTopRatedTvShowList(language) else null
        return response?.toDomain()
    }

    suspend fun getApiConfigFromApi(context: Context): ApiConfig? {
        val response = if (isNetworkAvailable(context)) api.getApiConfig() else null
        return response?.toDomain()
    }

    suspend fun getGenresTvFromApi(context: Context, language: String? = null): List<Genre>? {
        val response = if (isNetworkAvailable(context)) api.getGenresTv(language) else null
        return response?.genres?.map { it.toDomain() }
    }

    // Calls to DATABASE

    suspend fun getPopularTvShowsFromDatabase(): List<TvShow>? {
        val response = tvShowDao.getPopularTvShows()
        return if(response.isNotEmpty()) response.map { it.toDomain() } else null
    }

    suspend fun getPopularTvShowByIdFromDatabase(id: Int): TvShow? {
        val response = tvShowDao.getPopularTvShowByID(id.toString())
        return response.getOrNull(0)?.toDomain()
    }

    suspend fun getTopRatedTvShowByIdFromDatabase(id: Int): TvShow? {
        val response = tvShowDao.getTopRatedTvShowByID(id.toString())
        return response.getOrNull(0)?.toDomain()
    }

    suspend fun getTopRatedTvShowsFromDatabase(): List<TvShow>? {
        val response = tvShowDao.getTopRatedTvShows()
        return if(response.isNotEmpty()) response.map { it.toDomain() } else null
    }

    suspend fun getGenresFromDatabase(): List<Genre>? {
        val response = tvShowDao.getGenres()
        return if(response.isNotEmpty()) response.map { it.toDomain() } else null
    }

    suspend fun insertPopularTvShows(tvShows: List<PopularTvShowEntity>) {
        tvShowDao.insertPopularTvShowsResponse(tvShows)
    }

    suspend fun insertTopRatedTvShows(tvShows: List<TopRatedTvShowEntity>) {
        tvShowDao.insertTopRatedTvShowsResponse(tvShows)
    }

    suspend fun insertGenresTv(genres: List<GenreEntity>) {
        tvShowDao.insertGenresTv(genres)
    }

    suspend fun clearPopularTvShows() {
        tvShowDao.deletePopularTvShows()
    }

    suspend fun clearTopRatedTvShows() {
        tvShowDao.deleteTopRatedTvShows()
    }

    suspend fun clearGenresTv() {
        tvShowDao.deleteGenresTv()
    }
}