package com.hermosotech.filmjoy.domain

import com.hermosotech.filmjoy.data.model.TvShowProvider
import com.hermosotech.filmjoy.data.model.api.TvShowModel

class GetTvShowDetails() {

    operator fun invoke(index: Int): TvShowModel? {
        if (TvShowProvider.popularTvShowsResponse.results.isNotEmpty()) {
            if (index in 0 until TvShowProvider.popularTvShowsResponse.results.size)
                return TvShowProvider.popularTvShowsResponse.results[index]
        }

        return null
    }

}