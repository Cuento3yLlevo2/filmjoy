package com.hermosotech.filmjoy.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hermosotech.filmjoy.data.database.dao.TvShowDao
import com.hermosotech.filmjoy.data.database.entities.GenreEntity
import com.hermosotech.filmjoy.data.database.entities.PopularTvShowEntity
import com.hermosotech.filmjoy.data.database.entities.TopRatedTvShowEntity

@Database(entities = [PopularTvShowEntity::class, TopRatedTvShowEntity::class, GenreEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class TvShowDatabase: RoomDatabase() {

    abstract fun getTvShowDao(): TvShowDao
}