package com.hermosotech.filmjoy.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hermosotech.filmjoy.domain.GetPopularTvShows
import com.hermosotech.filmjoy.domain.GetTvShowDetails
import com.hermosotech.filmjoy.domain.model.TvShow
import com.hermosotech.filmjoy.domain.model.TvShowsResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
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
class TvShowViewModelTest {

    @RelaxedMockK
    private lateinit var getPopularTvShows: GetPopularTvShows

    @RelaxedMockK
    private lateinit var getTvShowDetails: GetTvShowDetails

    private lateinit var tvShowViewModel: TvShowViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        tvShowViewModel = TvShowViewModel(getPopularTvShows, getTvShowDetails)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created at the first time, get all tv shows`() = runTest{
        //Given
        val response = TvShowsResponse(1, listOf(
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
        ), 50, 11)

        coEvery { getPopularTvShows() } returns response.results

        //When
        tvShowViewModel.onCreate()

        //Then
        assert(tvShowViewModel.popularTvShows.value == response.results)
    }
}