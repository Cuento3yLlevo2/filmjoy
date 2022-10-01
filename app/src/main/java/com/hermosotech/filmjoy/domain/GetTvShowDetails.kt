package com.hermosotech.filmjoy.domain

import com.hermosotech.filmjoy.data.model.TvShowProvider
import com.hermosotech.filmjoy.data.model.api.TvShowModel
import javax.inject.Inject

class GetTvShowDetails @Inject constructor(private val tvShowProvider: TvShowProvider) {

    operator fun invoke(index: Int): TvShowModel? {
        if (tvShowProvider.response.results.isNotEmpty()) {
            if (index in 0 until tvShowProvider.response.results.size)
                return tvShowProvider.response.results[index]
        }

        return null
    }

}