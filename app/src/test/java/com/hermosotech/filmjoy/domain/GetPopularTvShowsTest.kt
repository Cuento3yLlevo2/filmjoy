package com.hermosotech.filmjoy.domain

import com.hermosotech.filmjoy.data.TvShowRepository
import com.hermosotech.filmjoy.domain.model.TvShow
import com.hermosotech.filmjoy.domain.model.TvShowsResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetPopularTvShowsTest {

    @RelaxedMockK
    private lateinit var tvShowRepository: TvShowRepository

    lateinit var getPopularTvShows: GetPopularTvShows

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getPopularTvShows = GetPopularTvShows(tvShowRepository)
    }

    @Test
    fun `when the api does not return anything then get values from database`() = runBlocking {
        // Given
        coEvery { tvShowRepository.getPopularTvShowsResponseFromApi(null) } returns TvShowsResponse(0, emptyList(), 0, 0)

        // When
        getPopularTvShows(null, null)

        // Then
        coVerify(exactly = 1) { tvShowRepository.getPopularTvShowsFromDatabase() }
        coVerify(exactly = 0) { tvShowRepository.clearPopularTvShows() }
        coVerify(exactly = 0) { tvShowRepository.insertPopularTvShows(any()) }
    }

    @Test
    fun `when the api return something then get values from api`() = runBlocking {
        // Given
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

        coEvery { tvShowRepository.getPopularTvShowsResponseFromApi(null) } returns response

        // When
        val result = getPopularTvShows(null, null)

        // Then
        coVerify(exactly = 1) { tvShowRepository.clearPopularTvShows() }
        coVerify(exactly = 1) { tvShowRepository.insertPopularTvShows(any()) }
        coVerify(exactly = 0) { tvShowRepository.getPopularTvShowsFromDatabase() }
        assert(result == response.results)
    }
}