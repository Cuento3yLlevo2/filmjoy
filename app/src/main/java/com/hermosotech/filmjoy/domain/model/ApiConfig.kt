package com.hermosotech.filmjoy.domain.model

import com.hermosotech.filmjoy.data.model.api.ApiConfigModel
import com.hermosotech.filmjoy.data.model.api.ImageConfigModel

data class ApiConfig(
    val imageConfig : ImageConfig?,
    val changeKeys: List<String>
)

fun ApiConfigModel.toDomain() = ApiConfig(images?.toDomain(), changeKeys)
