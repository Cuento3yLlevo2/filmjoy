package com.hermosotech.filmjoy.ui.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hermosotech.filmjoy.core.LocaleManager
import com.hermosotech.filmjoy.domain.ApiConfiguration
import com.hermosotech.filmjoy.domain.GetPopularTvShows
import com.hermosotech.filmjoy.domain.GetTopRatedTvShows
import com.hermosotech.filmjoy.domain.model.ApiConfig
import com.hermosotech.filmjoy.domain.model.TvShow
import com.hermosotech.filmjoy.domain.model.TvShowsResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @RelaxedMockK
    private lateinit var getPopularTvShows: GetPopularTvShows

    @RelaxedMockK
    private lateinit var getTopRatedTvShows: GetTopRatedTvShows

    @RelaxedMockK
    private lateinit var apiConfiguration: ApiConfiguration

    @RelaxedMockK
    private lateinit var localeManager: LocaleManager

    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        homeViewModel =
            HomeViewModel(localeManager, getPopularTvShows, getTopRatedTvShows, apiConfiguration)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun getAllTvShows() = runTest {
        //Given
        val response = TvShowsResponse(
            1, listOf(
                TvShow(
                    "/p",
                    47.43,
                    31,
                    "/p",
                    5.04,
                    "ov",
                    "2010-06-08",
                    listOf("US"),
                    listOf(18),
                    "en",
                    133,
                    "n",
                    "on"
                )
            ), 50, 11
        )

        coEvery { getPopularTvShows() } returns response.results

        //When
        homeViewModel.onCreate(null)

        //Then
        assert(homeViewModel.popularTvShows.value == response.results)
    }
}