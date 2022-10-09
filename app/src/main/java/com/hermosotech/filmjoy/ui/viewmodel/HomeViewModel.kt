package com.hermosotech.filmjoy.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hermosotech.filmjoy.core.LocaleManager
import com.hermosotech.filmjoy.domain.ApiConfiguration
import com.hermosotech.filmjoy.domain.GetPopularTvShows
import com.hermosotech.filmjoy.domain.GetTopRatedTvShows
import com.hermosotech.filmjoy.domain.model.TvShow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val localeManager: LocaleManager,
    private val getPopularTvShows: GetPopularTvShows,
    private val getTopRatedTvShows: GetTopRatedTvShows,
    val apiConfiguration: ApiConfiguration
): ViewModel() {

    val popularTvShows = MutableLiveData<List<TvShow>>()
    val topRatedTvShows = MutableLiveData<List<TvShow>>()
    val tvShow = MutableLiveData<TvShow>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate(context: Context? = null) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val language : String? = context?.let { localeManager.getCurrentLocate(context).language }

            apiConfiguration.getApiConfigFromApi()

            val popular = getPopularTvShows(language?.let { apiConfiguration.getLanguageTranslation(it) })

            popular?.let { popularShows ->
                popularTvShows.postValue(popularShows)

                val topRated = getTopRatedTvShows(language?.let { apiConfiguration.getLanguageTranslation(it) })

                topRated?.let { ratedShows ->
                    topRatedTvShows.postValue(ratedShows)
                    isLoading.postValue(false)
                }
            }
        }
    }

    fun getCurrentLanguage(context: Context): String {
        return localeManager.getCurrentLocate(context).language
    }
}