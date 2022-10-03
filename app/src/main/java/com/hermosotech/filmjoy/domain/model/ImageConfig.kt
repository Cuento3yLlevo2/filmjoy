package com.hermosotech.filmjoy.domain.model

import com.hermosotech.filmjoy.data.model.api.ImageConfigModel
import com.hermosotech.filmjoy.data.model.api.TvShowModel

data class ImageConfig(
    val baseUrl : String,
    val secureBaseUrl: String,
    val backdropSizes: List<String>,
    val logoSizes: List<String>,
    val posterSizes: List<String>,
    val profileSizes: List<String>,
    val stillSizes: List<String>
)

fun ImageConfigModel.toDomain() = ImageConfig(baseUrl, secureBaseUrl, backdropSizes, logoSizes, posterSizes, profileSizes, stillSizes)
