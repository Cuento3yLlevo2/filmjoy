package com.hermosotech.filmjoy.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hermosotech.filmjoy.data.database.entities.PopularTvShowEntity
import com.hermosotech.filmjoy.data.database.entities.TopRatedTvShowEntity

@Dao
/**
 * Data access object that the rest of the app uses to interact with data in the TvShows table.
 */
interface TvShowDao {

    @Query("SELECT * FROM popular_tv_shows_table")
    suspend fun getPopularTvShows(): List<PopularTvShowEntity>

    @Query("SELECT * FROM popular_tv_shows_table WHERE id=:id")
    suspend fun getPopularTvShowByID(id: String): List<PopularTvShowEntity>

    @Query("SELECT * FROM top_rated_tv_shows_table")
    suspend fun getTopRatedTvShows(): List<TopRatedTvShowEntity>

    @Query("SELECT * FROM top_rated_tv_shows_table WHERE id=:id")
    suspend fun getTopRatedTvShowByID(id: String): List<PopularTvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularTvShowsResponse(tvShows: List<PopularTvShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRatedTvShowsResponse(tvShows: List<TopRatedTvShowEntity>)

    @Query("DELETE FROM popular_tv_shows_table")
    suspend fun deletePopularTvShows()

    @Query("DELETE FROM top_rated_tv_shows_table")
    suspend fun deleteTopRatedTvShows()
}