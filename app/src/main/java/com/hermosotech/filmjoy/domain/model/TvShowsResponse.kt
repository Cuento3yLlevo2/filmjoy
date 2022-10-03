package com.hermosotech.filmjoy.domain.model

import com.hermosotech.filmjoy.data.model.api.TvShowsResponseModel

data class TvShowsResponse(
    val page : Int,
    val results: List<TvShow>,
    val totalResults : Int,
    val totalPages : Int
)

fun TvShowsResponseModel.toDomain() = TvShowsResponse(page, results.map { it.toDomain() }, totalResults, totalPages)