package com.hermosotech.filmjoy.domain.model

import android.os.Build
import com.hermosotech.filmjoy.data.database.entities.PopularTvShowEntity
import com.hermosotech.filmjoy.data.database.entities.TopRatedTvShowEntity
import com.hermosotech.filmjoy.data.model.api.TvShowModel
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*


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
fun PopularTvShowEntity.toDomain() = TvShow(posterPath, popularity, id, backdropPath, voteAverage, overview, firstAirDate, originCountry, genreIds, originalLanguage, voteCount, name, originalName)
fun TopRatedTvShowEntity.toDomain() = TvShow(posterPath, popularity, id, backdropPath, voteAverage, overview, firstAirDate, originCountry, genreIds, originalLanguage, voteCount, name, originalName)

fun TvShow.formatFirstAirDate(pattern: String, locale: Locale = Locale.US): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = formatter.parse(firstAirDate)
        DateTimeFormatter.ofPattern(pattern, locale).format(date)
    } else {
        val formatter = SimpleDateFormat("yyyy-MM-dd", locale)
        val date = formatter.parse(firstAirDate)
        val desiredFormat = date?.let { SimpleDateFormat(pattern, locale).format(it) }
        desiredFormat ?: firstAirDate
    }
}