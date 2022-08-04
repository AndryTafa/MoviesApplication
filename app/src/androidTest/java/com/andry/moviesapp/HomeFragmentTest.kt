package com.andry.moviesapp

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.andry.moviesapp.ui.HomeFragment
import com.andry.moviesapp.ui.adapter.MoviesViewHolder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @Before
    fun setup() {
        launchFragmentInContainer<HomeFragment>(themeResId = R.style.Theme_MoviesApp)
    }

    @Test
    fun recyclerViewShouldBeVisible() {
        onView(withId(R.id.movie_recycler))
            .check(matches(isDisplayed()))
    }

    @Test
    fun searchEditTextShouldBeVisible() {
        onView(withId(R.id.search_edit_text))
            .check(matches(isDisplayed()))
            .check(matches(withHint("Search")))
    }

    @Test
    fun validSearchShouldDisplayZeroResults() {
        onView(withId(R.id.search_edit_text))
            .check(matches(isDisplayed()))
            .perform(typeText("Dance Off 2"))

        onView(withId(R.id.movie_recycler))
            .check(matches(isDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<MoviesViewHolder>(1))
            .check(matches(hasChildCount(0)))
    }

    @Test
    fun validSearchShouldDisplayOneResult() {
        onView(withId(R.id.search_edit_text))
            .check(matches(isDisplayed()))
            .perform(typeText("dunkirk"))

        onView(withId(R.id.movie_recycler))
            .check(matches(isDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<MoviesViewHolder>(1))
            .check(matches(hasChildCount(1)))
    }
}