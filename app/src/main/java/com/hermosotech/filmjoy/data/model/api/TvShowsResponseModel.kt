package com.hermosotech.filmjoy.data.model.api

import com.google.gson.annotations.SerializedName

data class TvShowsResponseModel(
    @SerializedName("page") val page : Int,
    @SerializedName("results") val results: List<TvShowModel>,
    @SerializedName("total_results") val totalResults : Int,
    @SerializedName("total_pages") val totalPages : Int
)
