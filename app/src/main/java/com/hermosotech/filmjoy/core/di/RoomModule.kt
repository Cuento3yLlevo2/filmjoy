package com.hermosotech.filmjoy.core.di

import android.content.Context
import androidx.room.Room
import com.hermosotech.filmjoy.data.database.TvShowDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val TV_SHOW_DATABASE_NAME = "tv_show_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, TvShowDatabase::class.java, TV_SHOW_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideTvShowDao(db: TvShowDatabase) = db.getTvShowDao()
}