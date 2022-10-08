package com.hermosotech.filmjoy.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hermosotech.filmjoy.core.LocaleManager
import com.hermosotech.filmjoy.domain.ApiConfiguration
import com.hermosotech.filmjoy.domain.GetTvShowDetails
import com.hermosotech.filmjoy.domain.model.TvShow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowDetailViewModel @Inject constructor(
    private val localeManager: LocaleManager,
    private val getTvShowDetails: GetTvShowDetails,
    val apiConfiguration: ApiConfiguration
): ViewModel() {

    val tvShow = MutableLiveData<TvShow>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate(id : Int, tableName: String) {
        viewModelScope.launch {
            isLoading.postValue(true)

            apiConfiguration()

            val result = getTvShowDetails(id, tableName)

            result?.let {
                tvShow.postValue(it)
                isLoading.postValue(false)
            }
        }
    }

    fun getCurrentLanguage(context: Context): String {
        return localeManager.getCurrentLocate(context).language
    }
}