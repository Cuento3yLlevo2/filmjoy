package com.hermosotech.filmjoy.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hermosotech.filmjoy.domain.model.Genre

@Entity(tableName = "genres_tv_table")
/**
 *  Defines a List of Genres as data entity.
 */
data class GenreEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String
)

fun Genre.toDatabase() = GenreEntity(id, name)


