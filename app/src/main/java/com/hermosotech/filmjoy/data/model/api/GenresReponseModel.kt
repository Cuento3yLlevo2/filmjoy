package com.hermosotech.filmjoy.data.model.api

import com.google.gson.annotations.SerializedName

data class GenresReponseModel(
    @SerializedName("genres") val genres : List<GenreModel>
)