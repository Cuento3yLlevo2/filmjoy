package com.hermosotech.filmjoy.core

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.hermosotech.filmjoy.data.TvShowRepository
import com.hermosotech.filmjoy.domain.model.ApiConfig
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageHelper @Inject constructor(private val repository : TvShowRepository) {

    enum class ImageType {
        POSTER,
        BACKDROP,
        LOGO,
        PROFILE,
        STILL
    }

    private var apiConfig: ApiConfig? = null


    suspend fun getApiConfigFromApi(context: Context?) {
        context?.let {
            repository.getApiConfigFromApi(context)?.let { config ->
                apiConfig = config
            }
        }
    }

    fun storageBitmapInCacheDir(context: Context, imagePath: String, bitmap: Bitmap) {
        val file = File(context.cacheDir, imagePath)

        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fos)
            fos.close()
        } catch (e: IOException) {
            Log.e("FilmJoy-ImageHelper", e.stackTraceToString())
            if (fos != null) {
                try {
                    fos.close()
                } catch (e1: IOException) {
                    e1.printStackTrace()
                }
            }
        }
    }

    fun getBitmapFileFromStorageOrNull(context: Context, imagePath: String) : File? {
        val file = File(context.cacheDir, imagePath)
        return if (file.exists()) {
            file
        } else {
            null
        }
    }

    fun buildApiImageURL(imagePath: String, minSize: Int? = null, imageType: ImageType): String? {
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