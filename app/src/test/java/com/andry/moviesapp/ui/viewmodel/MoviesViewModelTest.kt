package com.andry.moviesapp.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.andry.moviesapp.data.MovieData
import com.andry.moviesapp.ui.repository.MoviesRepository
import io.mockk.*
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val moviesRepository: MoviesRepository = mockk {
        coEvery { getMovieResponseFromNetwork() } just Runs
        coEvery { getMoviesFromDb() } returns flow { emit(movieDataList) }
    }

    private val observer: Observer<List<MovieData>> = mockk(relaxed = true)

    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setup()  {
        viewModel = MoviesViewModel(moviesRepository)
        viewModel.movieEvent.observeForever(observer)
    }

    @After
    fun tearDown() {
       viewModel.movieEvent.removeObserver(observer)
    }

    @Test
    fun getMoviesFromDbShouldEmitListOfMovieTitles() = runBlocking {
        viewModel.getMoviesFromDb()
        verify { observer.onChanged(movieDataList) }
        assertEquals(movieDataList, viewModel.movieEvent.value)
    }

    @Test
    fun filterMoviesByGenreShouldReturnMatchingGenres() = runBlocking {
        val expectedList = listOf(
            MovieData(
                id = 0,
                year = "1984",
                title = "Coming to America",
                genre = "Comedy",
                poster = ""
            ),
            MovieData(
                id = 1,
                year = "2022",
                title = "Coming 2 America",
                genre = "Comedy",
                poster = ""
            )
        )
        viewModel.filterMovies("Comedy")
        verify { observer.onChanged(expectedList) }
        assertEquals(expectedList, viewModel.movieEvent.value)
    }

    @Test
    fun filterTitleByNameShouldReturnMatchingTitles() = runBlocking {
        val expectedList = listOf(
            MovieData(
                id = 1,
                year = "2022",
                title = "Coming 2 America",
                genre = "Comedy",
                poster = ""
            )
        )
        viewModel.filterMovies("Coming 2")
        verify { observer.onChanged(expectedList) }
        assertEquals(expectedList, viewModel.movieEvent.value)
    }

    @Test
    fun filterTitleByNameShouldNotReturnNonMatchingTitles() = runBlocking {
        val expectedList = listOf(
            MovieData(
                id = 2,
                year = "2020",
                title = "Lucky",
                genre = "Action",
                poster = ""
            )
        )
        viewModel.filterMovies("Coming")
        verify { observer.onChanged(any()) }
        assert(expectedList != viewModel.movieEvent.value)
    }

    private val movieDataList = listOf(
        MovieData(
            id = 0,
            year = "1984",
            title = "Coming to America",
            genre = "Comedy",
            poster = ""
        ),
        MovieData(
            id = 1,
            year = "2022",
            title = "Coming 2 America",
            genre = "Comedy",
            poster = ""
        ),
        MovieData(
            id = 2,
            year = "2020",
            title = "Lucky",
            genre = "Action",
            poster = ""
        )
    )
}