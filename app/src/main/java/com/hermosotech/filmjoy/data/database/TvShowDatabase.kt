package com.hermosotech.filmjoy.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hermosotech.filmjoy.data.database.dao.TvShowDao
import com.hermosotech.filmjoy.data.database.entities.PopularTvShowEntity
import com.hermosotech.filmjoy.data.database.entities.TopRatedTvShowEntity

@Database(entities = [PopularTvShowEntity::class, TopRatedTvShowEntity::class], version = 1)
abstract class TvShowDatabase: RoomDatabase() {

    abstract fun getTvShowDao(): TvShowDao
}