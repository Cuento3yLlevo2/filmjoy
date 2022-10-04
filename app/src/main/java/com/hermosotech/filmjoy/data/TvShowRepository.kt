package com.hermosotech.filmjoy.data

import com.hermosotech.filmjoy.data.database.dao.TvShowDao
import com.hermosotech.filmjoy.data.database.entities.PopularTvShowEntity
import com.hermosotech.filmjoy.data.database.entities.TopRatedTvShowEntity
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

    suspend fun getTopRatedTvShowsResponseFromApi(): TvShowsResponse? {
        val response = api.getTopRatedTvShowList()
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

    suspend fun getPopularTvShowByIdFromDatabase(id: Int): TvShow? {
        val response = tvShowDao.getPopularTvShowByID(id.toString())
        return response[0].toDomain()
    }

    suspend fun getTopRatedTvShowByIdFromDatabase(id: Int): TvShow? {
        val response = tvShowDao.getTopRatedTvShowByID(id.toString())
        return response[0].toDomain()
    }

    suspend fun getTopRatedTvShowsFromDatabase(): List<TvShow>? {
        val response = tvShowDao.getTopRatedTvShows()
        return response.map { it.toDomain() }
    }

    suspend fun insertPopularTvShows(tvShows: List<PopularTvShowEntity>) {
        tvShowDao.insertPopularTvShowsResponse(tvShows)
    }

    suspend fun insertTopRatedTvShows(tvShows: List<TopRatedTvShowEntity>) {
        tvShowDao.insertTopRatedTvShowsResponse(tvShows)
    }

    suspend fun clearPopularTvShows() {
        tvShowDao.deletePopularTvShows()
    }

    suspend fun clearTopRatedTvShows() {
        tvShowDao.deleteTopRatedTvShows()
    }
}