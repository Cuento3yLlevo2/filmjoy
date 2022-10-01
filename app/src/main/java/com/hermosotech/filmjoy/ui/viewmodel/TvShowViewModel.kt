package com.hermosotech.filmjoy.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hermosotech.filmjoy.data.model.api.TvShowModel
import com.hermosotech.filmjoy.domain.GetPopularTvShows
import com.hermosotech.filmjoy.domain.GetTvShowDetails
import kotlinx.coroutines.launch

class TvShowViewModel : ViewModel() {

    val tvShowModelList = MutableLiveData<List<TvShowModel>>()
    val tvShow = MutableLiveData<TvShowModel>()
    val isLoading = MutableLiveData<Boolean>()

    private var getPopularTvShows = GetPopularTvShows()
    private var getTvShowDetails = GetTvShowDetails()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getPopularTvShows()

            result?.let {
                tvShowModelList.postValue(it.results)
                isLoading.postValue(false)
            }
        }
    }

    fun getTvShow(index: Int) {
        isLoading.postValue(true)
        val result = getTvShowDetails(index)

        result?.let {
            tvShow.postValue(it)
            isLoading.postValue(false)
        }
    }
}