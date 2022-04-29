package com.example.perekrestki;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Test
    public void mainActivitySettingsButtonClickUITest() {
        ViewInteraction appCompatButton = onView(withId(R.id.settings_button));
        appCompatButton.perform(click());
        onView(withId(R.id.settingsTxt)).check(matches(isDisplayed()));
    }
    @Test
    public void mainActivityPddButtonClickUITest() {
        ViewInteraction appCompatButton = onView(withId(R.id.pdd_button));
        appCompatButton.perform(click());
        onView(withId(R.id.topPanelTxt)).check(matches(isDisplayed()));
    }
    @Test
    public void mainActivityStartButtonClickUITest() {
        ViewInteraction appCompatButton = onView(withId(R.id.start_button));
        appCompatButton.perform(click());
        onView(withId(R.id.lvl1_btn)).check(matches(isDisplayed()));
    }
    @Test
    public void mainActivityPressBackTest(){
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        Instrumentation.ActivityMonitor activityMonitor = instrumentation.addMonitor(MainActivity.class.getName(), null, false);
        Activity activity = instrumentation.waitForMonitorWithTimeout(activityMonitor, 1000);
        Espresso.pressBackUnconditionally();
        if(activity != null) {
            // do something
            fail();
        }
    }
}
