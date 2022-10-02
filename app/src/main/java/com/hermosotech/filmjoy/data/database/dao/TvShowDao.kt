package com.hermosotech.filmjoy.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hermosotech.filmjoy.data.database.entities.TvShowEntity

@Dao
/**
 * Data access object that the rest of the app uses to interact with data in the TvShows table.
 */
interface TvShowDao {

    @Query("SELECT * FROM popular_tv_shows_table")
    suspend fun getPopularTvShows(): List<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularTvShowsResponse(tvShows: List<TvShowEntity>)

    @Query("DELETE FROM popular_tv_shows_table")
    suspend fun deletePopularTvShows()
}