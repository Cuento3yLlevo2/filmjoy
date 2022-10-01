package com.hermosotech.filmjoy.data.model

import com.hermosotech.filmjoy.data.model.api.PopularTvShowsResponse
import com.hermosotech.filmjoy.data.model.api.TvShowModel

class TvShowProvider {
    companion object {
        var popularTvShowsResponse = PopularTvShowsResponse(0, emptyList(), 0, 0)
    }
}