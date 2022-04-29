package com.example.perekrestki;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LevelPickActivityUITest {
    @Rule
    public ActivityTestRule<LevelPickActivity> lpActivityTestRule = new ActivityTestRule<>(LevelPickActivity.class);
    @Test
    public void levelPickActivityLevelButtonClickUITest() {
        String TEXT = lpActivityTestRule.getActivity().getString(R.string.level_string)+ " 1";
        onView(withId(R.id.lvl1_btn)).perform(click());
        onView(withText(TEXT)).check(matches(isDisplayed()));
    }
    @Test
    public void levelPickActivityCompletedLevelButtonClickUITest(){
        DBHelper db = new DBHelper(lpActivityTestRule.getActivity());
        db.updateUserStat(1,1,0,0);
        onView(withId(R.id.lvl1_btn)).perform(click());
        onView(withId(R.id.statusText)).check(matches(withText("Пройдено")));
    }
    @Test
    public void levelPickActivityInfinityButtonClickUITest() {
        String TEXT = lpActivityTestRule.getActivity().getString(R.string.infinity_string);
        onView(withId(R.id.infinity_btn)).perform(click());
        onView(withText(TEXT)).check(matches(isDisplayed()));
    }
    @Test
    public void levelPickActivityMapButtonClickUITest() {
        String TEXT = lpActivityTestRule.getActivity().getString(R.string.map_string);
        onView(withId(R.id.map_button)).perform(click());
        onView(withText(TEXT)).check(matches(isDisplayed()));
    }
}
