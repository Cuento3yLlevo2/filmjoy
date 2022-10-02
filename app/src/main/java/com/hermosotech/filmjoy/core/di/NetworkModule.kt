package com.hermosotech.filmjoy.core.di

import com.hermosotech.filmjoy.data.network.TvShowApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
/**
 * Provides API Client instance using DI.
 */
object NetworkModule {

    const val API_BASE_URL = "https://api.themoviedb.org/3/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideTvShowApiClient(retrofit: Retrofit): TvShowApiClient {
        return retrofit.create(TvShowApiClient::class.java)
    }
}