package com.hermosotech.filmjoy.domain

import com.hermosotech.filmjoy.data.TvShowRepository
import com.hermosotech.filmjoy.domain.model.ApiConfig
import javax.inject.Inject

class GetApiConfiguration @Inject constructor(private val repository : TvShowRepository) {

    suspend operator fun invoke() : ApiConfig? {
        val apiConfig = repository.getApiConfigFromApi()

        apiConfig?.imageConfig?.let {
            return apiConfig
        }

        return null
    }

}