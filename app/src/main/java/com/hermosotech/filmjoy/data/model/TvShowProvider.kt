package com.hermosotech.filmjoy.data.model

import com.hermosotech.filmjoy.data.model.api.PopularTvShowsResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvShowProvider @Inject constructor() {
    var response = PopularTvShowsResponse(0, emptyList(), 0, 0)
}