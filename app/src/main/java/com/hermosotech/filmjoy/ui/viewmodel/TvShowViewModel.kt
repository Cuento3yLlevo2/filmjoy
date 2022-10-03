package com.hermosotech.filmjoy.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hermosotech.filmjoy.domain.GetApiConfiguration
import com.hermosotech.filmjoy.domain.GetPopularTvShows
import com.hermosotech.filmjoy.domain.GetTvShowDetails
import com.hermosotech.filmjoy.domain.model.ApiConfig
import com.hermosotech.filmjoy.domain.model.TvShow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(
    private val getPopularTvShows: GetPopularTvShows,
    private val getTvShowDetails: GetTvShowDetails,
    private val getApiConfiguration: GetApiConfiguration
): ViewModel() {

    val popularTvShows = MutableLiveData<List<TvShow>>()
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

            val result = getPopularTvShows()

            result?.let { tvShows ->
                popularTvShows.postValue(tvShows)
                isLoading.postValue(false)
            }
        }
    }

    fun getTvShow(index: Int) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getTvShowDetails(index)

            result?.let {
                tvShow.postValue(it)
                isLoading.postValue(false)
            }
        }
    }
}