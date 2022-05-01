package com.example.perekrestki;


import android.app.Activity;
import android.app.Instrumentation;
import android.widget.Button;

import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    private MainActivity mActivity = null;
    private Instrumentation.ActivityMonitor levelPickMonitor = InstrumentationRegistry.getInstrumentation().addMonitor(LevelPickActivity.class.getName(),null,false);
    private Instrumentation.ActivityMonitor pddMonitor = InstrumentationRegistry.getInstrumentation().addMonitor(PddPageActivity.class.getName(),null,false);
    private Instrumentation.ActivityMonitor settingsMonitor = InstrumentationRegistry.getInstrumentation().addMonitor(SettingsActivity.class.getName(),null,false);

    @Before
    public void setUp() throws Exception{
        mActivity = mActivityTestRule.getActivity();
    }
    @Test
    public void mainActivitySettingsButtonClickUITest() {
        Assert.assertNotNull(mActivity.findViewById(R.id.settings_button));
        mActivity.goSettingsPage(new Button(mActivity));
        Activity settingsActivity = InstrumentationRegistry.getInstrumentation()
                .waitForMonitorWithTimeout(settingsMonitor,5000);
        Assert.assertNotNull(settingsActivity);
        settingsActivity.finish();
    }
    @Test
    public void mainActivityPddButtonClickUITest() {
        Assert.assertNotNull(mActivity.findViewById(R.id.pdd_button));
        mActivity.goPddPage(new Button(mActivity));
        Activity pddActivity = InstrumentationRegistry.getInstrumentation()
                .waitForMonitorWithTimeout(pddMonitor,5000);
        Assert.assertNotNull(pddActivity);
        pddActivity.finish();
    }
    @Test
    public void mainActivityStartButtonClickUITest() {
        Assert.assertNotNull(mActivity.findViewById(R.id.start_button));
        mActivity.goLvlPick(new Button(mActivity));
        Activity levelPickActivity = InstrumentationRegistry.getInstrumentation()
                .waitForMonitorWithTimeout(levelPickMonitor,5000);
        Assert.assertNotNull(levelPickActivity);
        levelPickActivity.finish();
    }
    @After
    public void tearDown() throws Exception{
        mActivity = null;
    }
}
