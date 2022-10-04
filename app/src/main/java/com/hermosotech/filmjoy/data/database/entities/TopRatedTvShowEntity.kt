package com.hermosotech.filmjoy.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hermosotech.filmjoy.domain.model.TvShow
import com.hermosotech.filmjoy.domain.model.TvShowsResponse

@Entity(tableName = "top_rated_tv_shows_table")
/**
 *  Defines a TvShow data entity. Each instance of TvShow represents a row in a popular_tv_shows_table in the app's database.
 */
data class TopRatedTvShowEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "poster_path") val posterPath: String?,
    @ColumnInfo(name = "popularity") val popularity: Double,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String?,
    @ColumnInfo(name = "vote_average") val voteAverage: Double,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "first_air_date") val firstAirDate: String,
    @ColumnInfo(name = "origin_country") val originCountry: String, // for simplicity will only add one originCountry
    @ColumnInfo(name = "genre_ids") val genreIds: Int, // for simplicity will only add one genreID
    @ColumnInfo(name = "original_language") val originalLanguage: String,
    @ColumnInfo(name = "vote_count") val voteCount: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "original_name") val originalName: String
)

fun TvShowsResponse.toDatabaseTopRated() = results.map { it.toDatabaseTopRated() }
fun TvShow.toDatabaseTopRated() = TopRatedTvShowEntity(id, posterPath, popularity, backdropPath, voteAverage, overview, firstAirDate, originCountry[0], genreIds[0], originalLanguage, voteCount, name, originalName)

