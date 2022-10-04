package com.hermosotech.filmjoy.domain.model

import com.hermosotech.filmjoy.data.database.entities.PopularTvShowEntity
import com.hermosotech.filmjoy.data.database.entities.TopRatedTvShowEntity
import com.hermosotech.filmjoy.data.model.api.TvShowModel


data class TvShow(
    val posterPath: String?,
    val popularity: Double,
    val id: Int,
    val backdropPath: String?,
    val voteAverage: Double,
    val overview: String,
    val firstAirDate: String,
    val originCountry: List<String>,
    val genreIds: List<Int>,
    val originalLanguage: String,
    val voteCount: Int,
    val name: String,
    val originalName: String
)

fun TvShowModel.toDomain() = TvShow(posterPath, popularity, id, backdropPath, voteAverage, overview, firstAirDate, originCountry, genreIds, originalLanguage, voteCount, name, originalName)
fun PopularTvShowEntity.toDomain() = TvShow(posterPath, popularity, id, backdropPath, voteAverage, overview, firstAirDate, listOf(originCountry), listOf(genreIds), originalLanguage, voteCount, name, originalName)
fun TopRatedTvShowEntity.toDomain() = TvShow(posterPath, popularity, id, backdropPath, voteAverage, overview, firstAirDate, listOf(originCountry), listOf(genreIds), originalLanguage, voteCount, name, originalName)