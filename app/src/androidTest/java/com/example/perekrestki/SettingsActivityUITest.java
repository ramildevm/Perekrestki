package com.example.perekrestki;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static com.example.perekrestki.CustomMatcher.withNotBgColor;

import static org.hamcrest.CoreMatchers.not;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SettingsActivityUITest {
    @Rule
    public ActivityTestRule<SettingsActivity> mActivityTestRule = new ActivityTestRule<>(SettingsActivity.class);
    @Test
    public void settingsActivityChangeBackColorClickUITest() {
        ThemesSwitcher.updateDataFromDb(new DBHelper(mActivityTestRule.getActivity()));
        onView(withId(R.id.colorBackBtn)).perform(click());
        int color = ThemesSwitcher.layoutBackColor;
        onView(withId(R.id.mainBack)).check(matches(withNotBgColor(color)));
    }
    @Test
    public void settingsActivityChangeColorClickUITest() {
        ThemesSwitcher.updateDataFromDb(new DBHelper(mActivityTestRule.getActivity()));
        onView(withId(R.id.colorBtn)).perform(click());
        int color = ThemesSwitcher.mainColor;
        onView(withId(R.id.colorBtn)).check(matches(withNotBgColor(color)));
    }
    @Test
    public void settingsActivityStatButtonClickUITest() {
        ViewInteraction appCompatButton = onView(withId(R.id.stat_button));
        appCompatButton.perform(click());
        onView(withId(R.id.topPanelTxt)).check(matches(isDisplayed()));
    }
    @Test
    public void settingsActivityBackButtonClickUITest() {
        ViewInteraction appCompatButton = onView(withId(R.id.go_back_btn));
        appCompatButton.perform(click());
        onView(withId(R.id.start_button)).check(matches(isDisplayed()));
    }
    @Test
    public void settingsActivityBackPressedUITest() {
        ViewInteraction appCompat = onView(withId(R.id.start_button));
        Espresso.pressBack();
        appCompat.check(matches(isDisplayed()));
    }
    @Test
    public void settingsActivityChangeBackColorForMainActivityUITest() {
        ThemesSwitcher.updateDataFromDb(new DBHelper(mActivityTestRule.getActivity()));
        onView(withId(R.id.colorBackBtn)).perform(click());
        int color = ThemesSwitcher.layoutBackColor;
        Espresso.pressBack();
        onView(withId(R.id.mainBack)).check(matches(withNotBgColor(color)));
    }
}
