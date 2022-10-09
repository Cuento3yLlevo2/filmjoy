package com.hermosotech.filmjoy.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hermosotech.filmjoy.core.LocaleManager
import com.hermosotech.filmjoy.domain.ApiConfiguration
import com.hermosotech.filmjoy.domain.GetGenresTv
import com.hermosotech.filmjoy.domain.GetTvShowDetails
import com.hermosotech.filmjoy.domain.model.TvShow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowDetailViewModel @Inject constructor(
    private val getGenresTv: GetGenresTv,
    private val localeManager: LocaleManager,
    private val getTvShowDetails: GetTvShowDetails,
    private val apiConfiguration: ApiConfiguration
): ViewModel() {

    val tvShow = MutableLiveData<TvShow>()
    val genres = MutableLiveData<List<String>>()

    fun onCreate(id : Int, tableName: String) {
        viewModelScope.launch {

            getGenresTv()

            val result = getTvShowDetails(id, tableName)
            result?.let {
                tvShow.postValue(it)
            }
        }
    }

    fun getCurrentLanguage(context: Context): String {
        return localeManager.getCurrentLocate(context).language
    }

    fun getGenresById(ids: List<Int>) {
        viewModelScope.launch {
            getGenresTv.getGenresById(ids)?.let {
                genres.postValue(it)
            }
        }
    }

    fun getImageURL(imagePath: String?, minSize: Int? = null, imageType: ApiConfiguration.ImageType): String? {
        return apiConfiguration.getImageURL(imagePath, minSize, imageType)
    }
}