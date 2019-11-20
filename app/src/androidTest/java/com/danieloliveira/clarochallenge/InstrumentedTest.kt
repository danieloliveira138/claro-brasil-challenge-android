package com.danieloliveira.clarochallenge

import android.os.SystemClock
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.danieloliveira.clarochallenge.ui.activitys.MainActivity
import org.hamcrest.core.IsNot.not

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

@RunWith(AndroidJUnit4::class)
@LargeTest
class InstrumentedTest {

    private val delay = 1000L
    @get:Rule
    var rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityDrawerMenuTest() {
        SystemClock.sleep(delay)
        // When application open the drawer menu must be hide
        onView(withId(R.id.navigationMenu)).check(matches(not(isDisplayed())))
        // Open drawer menu
        onView(withId(R.id.drawerLayout)).perform(DrawerActions.open())
        // Check if drawer menu elements is displayed
        onView(withId(R.id.navigationMenu)).check(matches(isDisplayed()))
        onView(withId(R.id.radioOptions)).check(matches(isDisplayed()))
        onView(withId(R.id.popular)).check(matches(isDisplayed()))
        onView(withId(R.id.topRated)).check(matches(isDisplayed()))
        onView(withId(R.id.upComing)).check(matches(isDisplayed()))
        onView(withId(R.id.adultContentSwitch)).check(matches(isDisplayed()))
        onView(withText(R.string.permitir_conteudo_adulto)).check(matches(isDisplayed()))
        // Close the drawer
        onView(withId(R.id.drawerLayout)).perform(DrawerActions.close())
        // Certified menu is closed
        onView(withId(R.id.navigationMenu)).check(matches(not(isDisplayed())))
    }

    @Test
    fun mainActivitySearchBarTest() {
        SystemClock.sleep(delay)
        // Certified searchBar is hide
        onView(withId(R.id.searchView)).check(matches(isDisplayed()))
        onView(withId(R.id.actionSearch)).perform(click())
        SystemClock.sleep(delay)
        onView(withId(R.id.drawerLayout)).perform(DrawerActions.open())
        onView(withId(R.id.popular)).perform(click())
        onView(withId(R.id.drawerLayout)).perform(DrawerActions.open())
        SystemClock.sleep(delay)
        onView(withId(R.id.recycler)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19))
        SystemClock.sleep(delay)
        onView(withId(R.id.recycler)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        SystemClock.sleep(delay)
        onView(withId(R.id.drawerLayout)).perform(DrawerActions.open())
        onView(withId(R.id.topRated)).perform(click())
        onView(withId(R.id.drawerLayout)).perform(DrawerActions.close())
        SystemClock.sleep(delay)
        onView(withId(R.id.recycler)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19))
        onView(withId(R.id.recycler)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(19, click()))
        SystemClock.sleep(delay)
        onView(withId(R.id.favoriteMovie)).perform(click())
        onView(withId(R.layout.activity_detail)).perform(pressBack())
        SystemClock.sleep(delay)
        onView(withId(R.id.favoriteList)).perform(click())
        SystemClock.sleep((delay))

    }
}
