package com.hermosotech.filmjoy.data

import android.util.Log
import com.hermosotech.filmjoy.data.database.dao.TvShowDao
import com.hermosotech.filmjoy.data.database.entities.TvShowEntity
import com.hermosotech.filmjoy.data.network.TvShowService
import com.hermosotech.filmjoy.domain.model.ApiConfig
import com.hermosotech.filmjoy.domain.model.TvShow
import com.hermosotech.filmjoy.domain.model.TvShowsResponse
import com.hermosotech.filmjoy.domain.model.toDomain
import javax.inject.Inject

/**
 * This repository will have the logic to fetch the network results, manage the offline cache and to keep the database up-to-date.
 */
class TvShowRepository @Inject constructor(
    private val api: TvShowService,
    private val tvShowDao: TvShowDao
    ) {

    suspend fun getPopularTvShowsResponseFromApi(): TvShowsResponse? {
        val response = api.getPopularTvShowList()
        return response.toDomain()
    }

    suspend fun getApiConfigFromApi(): ApiConfig? {
        val response = api.getApiConfig()
        return response.toDomain()
    }

    suspend fun getPopularTvShowsFromDatabase(): List<TvShow>? {
        val response = tvShowDao.getPopularTvShows()
        return response.map { it.toDomain() }
    }

    suspend fun insertPopularTvShows(tvShows: List<TvShowEntity>) {
        tvShowDao.insertPopularTvShowsResponse(tvShows)
    }

    suspend fun clearPopularTvShows() {
        tvShowDao.deletePopularTvShows()
    }
}