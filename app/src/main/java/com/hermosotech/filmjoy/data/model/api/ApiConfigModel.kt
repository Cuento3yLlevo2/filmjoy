package com.hermosotech.filmjoy.data.model.api

import com.google.gson.annotations.SerializedName

data class ApiConfigModel(
    @SerializedName("images") val images : ImageConfigModel?,
    @SerializedName("change_keys") val changeKeys: List<String>
)
