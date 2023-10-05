package com.example.temperatureconverter

import org.junit.Assert.*
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import androidx.test.espresso.ViewAction
import org.hamcrest.Matcher
import android.view.View
import android.widget.SeekBar
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.UiController
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Test


fun setSeekBarProgress(progress: Int): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return isAssignableFrom(SeekBar::class.java)
        }

        override fun getDescription(): String {
            return "Set progress on SeekBar"
        }

        override fun perform(uiController: UiController, view: View) {
            val seekBar = view as SeekBar
            seekBar.progress = progress
        }
    }
}
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = launch(MainActivity::class.java)
    }
    @Test
    fun showscelToFah() {
        onView(withId(R.id.sBcel)).perform(setSeekBarProgress(30))
        onView(withId(R.id.fahnum)).check(matches(withText("86°F")))
    }

    @Test
    fun showFahTocel() {
        onView(withId(R.id.sBfah)).perform(setSeekBarProgress(86))
        onView(withId(R.id.celnum)).check(matches(withText("30°C")))
    }

    @After
    fun tearDown() {
        scenario.close()
    }
}