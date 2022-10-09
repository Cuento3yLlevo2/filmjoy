package com.hermosotech.filmjoy.domain

import com.hermosotech.filmjoy.data.TvShowRepository
import com.hermosotech.filmjoy.domain.model.ApiConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiConfiguration @Inject constructor(private val repository : TvShowRepository) {

    enum class ImageType {
        POSTER,
        BACKDROP,
        LOGO,
        PROFILE,
        STILL
    }

    private var apiConfig: ApiConfig? = null


    suspend fun getApiConfigFromApi() {
        apiConfig = repository.getApiConfigFromApi()
    }

    fun getImageURL(imagePath: String?, minSize: Int? = null, imageType: ImageType): String? {
        apiConfig?.imageConfig?.let { it ->
            val wSizes = when(imageType) {
                ImageType.POSTER -> it.posterSizes
                ImageType.BACKDROP -> it.backdropSizes
                ImageType.LOGO -> it.logoSizes
                ImageType.PROFILE -> it.profileSizes
                ImageType.STILL -> it.stillSizes
            }

            val size = getImageSize(wSizes, minSize)
            return it.secureBaseUrl + size + imagePath
        }

        return null
    }

    fun getLanguageTranslation(localeLanguage: String): String? {
        return when(localeLanguage) {
            "en" -> "en-US"
            "es" -> "es-ES"
            "ca" -> "ca-ES"
            "" -> null
            else -> "en-US"
        }
    }

    private fun getImageSize(wSizes: List<String>, minSize: Int?): String {
        minSize?.let {
            for (wSize in wSizes) {
                if (wSize == "original") {
                    return wSize
                }

                wSize.substringAfter("w").toIntOrNull()?.let { intSize ->
                    if (intSize >= minSize) {
                        return wSize
                    }
                }
            }
        }

        return wSizes.last()
    }
}