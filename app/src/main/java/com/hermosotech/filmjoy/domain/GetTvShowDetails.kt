package com.hermosotech.filmjoy.domain

import com.hermosotech.filmjoy.core.di.RoomModule.POPULAR_TV_SHOW_TABLE_NAME
import com.hermosotech.filmjoy.core.di.RoomModule.TOP_RATED_TV_SHOW_TABLE_NAME
import com.hermosotech.filmjoy.data.TvShowRepository
import com.hermosotech.filmjoy.domain.model.TvShow
import javax.inject.Inject

class GetTvShowDetails @Inject constructor(private val repository: TvShowRepository) {

    suspend operator fun invoke(id: Int, tableName: String): TvShow? {
        return when(tableName) {
            POPULAR_TV_SHOW_TABLE_NAME -> repository.getPopularTvShowByIdFromDatabase(id)
            TOP_RATED_TV_SHOW_TABLE_NAME -> repository.getTopRatedTvShowByIdFromDatabase(id)
            else -> null
        }
    }

}