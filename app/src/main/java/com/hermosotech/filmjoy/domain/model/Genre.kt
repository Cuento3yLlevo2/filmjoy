package com.hermosotech.filmjoy.domain.model

import com.hermosotech.filmjoy.data.database.entities.GenreEntity
import com.hermosotech.filmjoy.data.model.api.GenreModel

data class Genre(
   val id: Int,
   val name: String,
)

fun GenreModel.toDomain() = Genre(id, name)
fun GenreEntity.toDomain() = Genre(id, name)
