package com.example.perekrestki;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static androidx.test.runner.lifecycle.Stage.RESUMED;

import android.app.Activity;
import android.app.Instrumentation;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;

import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SettingsActivityUITest {
    @Rule
    public ActivityTestRule<SettingsActivity> sActivityTestRule = new ActivityTestRule<>(SettingsActivity.class);
    private SettingsActivity sActivity= null;
    private Cursor initialSettingsState;
    private Instrumentation.ActivityMonitor mainActivityMonitor = InstrumentationRegistry.getInstrumentation().addMonitor(MainActivity.class.getName(),null,false);
    private Instrumentation.ActivityMonitor statMonitor = InstrumentationRegistry.getInstrumentation().addMonitor(UserStatActivity.class.getName(),null,false);
    //private Instrumentation.ActivityMonitor settingsMonitor = InstrumentationRegistry.getInstrumentation().addMonitor(SettingsActivity.class.getName(),null,false);

    @Before
    public void setUp() throws Exception{
        sActivity = sActivityTestRule.getActivity();
        initialSettingsState = new DBHelper(sActivity).getUserSettings();
    }
    @Test
    public void settingsActivityChangeBackColorClickUITest() {
        ThemesSwitcher.updateDataFromDb(new DBHelper(sActivity));
        ColorDrawable buttonColor = (ColorDrawable)
                (sActivity.findViewById(R.id.colorBackBtn)).getBackground();
        int noexcept = buttonColor.getColor();
        Button button = sActivity.findViewById(R.id.colorBackBtn);
        sActivity.changeBackColor(button);
        ColorDrawable actualColor = (ColorDrawable) button.getBackground();
        int actual = actualColor.getColor();
        Assert.assertNotEquals(noexcept,actual);
    }
    @Test
    public void settingsActivityChangeColorClickUITest() {
        ThemesSwitcher.updateDataFromDb(new DBHelper(sActivity));
        ColorDrawable buttonColor = (ColorDrawable)
                (sActivity.findViewById(R.id.colorBtn)).getBackground();
        int noexcept = buttonColor.getColor();
        Button button = sActivity.findViewById(R.id.colorBtn);
        sActivity.changeColor(button);
        ColorDrawable actualColor = (ColorDrawable) button.getBackground();
        int actual = actualColor.getColor();
        Assert.assertNotEquals(noexcept,actual);
    }

    @Test
    public void settingsActivityBackButtonClickUITest() {
        sActivity.goMain(new Button(sActivity));
        Activity mainActivity = InstrumentationRegistry.getInstrumentation()
                .waitForMonitorWithTimeout(mainActivityMonitor,5000);
        Assert.assertNotNull(mainActivity);
        mainActivity.finish();
    }

    @Test
    public void settingsActivityBackPressedUITest() {
        sActivity.onBackPressed();
        Activity mainActivity = InstrumentationRegistry.getInstrumentation()
                .waitForMonitorWithTimeout(mainActivityMonitor,5000);
        Assert.assertNotNull(mainActivity);
        mainActivity.finish();
    }
    @Test
    public void settingsActivityGoStatButtonClickUITest() {
        sActivity.goStatPage(new Button(sActivity));
        Activity statActivity = InstrumentationRegistry.getInstrumentation().
                waitForMonitorWithTimeout(statMonitor,5000);
        Assert.assertNotNull(statActivity);
        statActivity.finish();
    }
    @Test
    public void settingsActivityChangeBackColorForMainActivityUITest() {
        ThemesSwitcher.updateDataFromDb(new DBHelper(sActivity));
        sActivity.changeBackColor(new Button(sActivity));
        sActivity.onBackPressed();
        Activity mainActivity = InstrumentationRegistry.getInstrumentation()
                .waitForMonitorWithTimeout(mainActivityMonitor,5000);
        View mainv = mainActivity.findViewById(R.id.mainBack);
        Assert.assertEquals(ThemesSwitcher.layoutBackColor,((ColorDrawable)(mainv.getBackground())).getColor());
    }
    @After
    public void tearDown() throws Exception{
        initialSettingsState.moveToFirst();
        DBHelper _db = new DBHelper(sActivity);
        _db.updateUserSettings(initialSettingsState.getInt(0),
                initialSettingsState.getString(1),
                initialSettingsState.getString(2));
        ThemesSwitcher.updateDataFromDb(_db);
        _db.close();
        sActivity = null;
    }
}
