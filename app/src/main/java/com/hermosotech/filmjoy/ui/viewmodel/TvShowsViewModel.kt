package com.hermosotech.filmjoy.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hermosotech.filmjoy.domain.GetApiConfiguration
import com.hermosotech.filmjoy.domain.GetPopularTvShows
import com.hermosotech.filmjoy.domain.GetTopRatedTvShows
import com.hermosotech.filmjoy.domain.GetTvShowDetails
import com.hermosotech.filmjoy.domain.model.ApiConfig
import com.hermosotech.filmjoy.domain.model.TvShow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowsViewModel @Inject constructor(
    private val getPopularTvShows: GetPopularTvShows,
    private val getTopRatedTvShows: GetTopRatedTvShows,
    private val getApiConfiguration: GetApiConfiguration
): ViewModel() {

    val popularTvShows = MutableLiveData<List<TvShow>>()
    val topRatedTvShows = MutableLiveData<List<TvShow>>()
    val apiConfiguration = MutableLiveData<ApiConfig>()
    val tvShow = MutableLiveData<TvShow>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)

            val apiConfig = getApiConfiguration()

            apiConfig?.let {
                apiConfiguration.postValue(it)
            }

            val popular = getPopularTvShows()

            popular?.let { popularShows ->
                popularTvShows.postValue(popularShows)

                val topRated = getTopRatedTvShows()

                topRated?.let { ratedShows ->
                    topRatedTvShows.postValue(ratedShows)
                    isLoading.postValue(false)
                }
            }
        }
    }
}