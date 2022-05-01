package com.example.perekrestki;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static com.example.perekrestki.CompareUtils.areDrawablesIdentical;

import android.app.Activity;
import android.app.Instrumentation;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
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
public class LevelPickActivityUITest {
    @Rule
    public ActivityTestRule<LevelPickActivity> lpActivityTestRule = new ActivityTestRule<>(LevelPickActivity.class);

    private LevelPickActivity lpActivity= null;
    private Instrumentation.ActivityMonitor levelInfoMonitor = InstrumentationRegistry.getInstrumentation().addMonitor(LevelInfoActivity.class.getName(),null,false);
    private Instrumentation.ActivityMonitor levelMonitor = InstrumentationRegistry.getInstrumentation().addMonitor(LevelActivity.class.getName(),null,false);

    @Before
    public void setUp() throws Exception{
        lpActivity = lpActivityTestRule.getActivity();
    }
    @Test
    public void levelPickActivityCheckButtonsMethodTest() {
        //get & update data from db
        DBHelper db = new DBHelper(lpActivityTestRule.getActivity());
        Cursor res = db.getUserStat();
        res.moveToFirst();
        db.updateUserStat(1,5,0,0);
        //assertion block
        int actual = lpActivity.checkButtons();
        Assert.assertEquals(5,actual);
        //remove data
        db.updateUserStat(res.getInt(0),res.getInt(1),
                res.getInt(2),res.getInt(3));
    }
    @Test
    public void levelPickActivitySetViewThemeMethodTest(){
        lpActivity.setViewTheme();
        View mainback = lpActivity.findViewById(R.id.mainBack);
        View infbtn = lpActivity.findViewById(R.id.infinity_btn);
        Assert.assertEquals(ThemesSwitcher.layoutBackColor,
                ((ColorDrawable)(mainback.getBackground())).getColor());
        Assert.assertTrue(areDrawablesIdentical(ContextCompat.getDrawable(lpActivity,ThemesSwitcher.backColor),
                infbtn.getBackground()));
    }
    @Test
    public void levelPickActivityLevelButtonClickUITest() {
        Assert.assertNotNull(lpActivity.findViewById(R.id.lvl1_btn));
        Button button = new Button(lpActivity);
        button.setText("1");
        lpActivity.goLvl(button);
        Activity levelInfoActivity = InstrumentationRegistry.getInstrumentation()
                .waitForMonitorWithTimeout(levelInfoMonitor,5000);
        Assert.assertNotNull(levelInfoActivity);
        levelInfoActivity.finish();
    }
    @Test
    public void levelPickActivityCompletedLevelButtonClickUITest(){
        //get & update data from db
        DBHelper db = new DBHelper(lpActivityTestRule.getActivity());
        Cursor res = db.getUserStat();
        res.moveToFirst();
        db.updateUserStat(1,1,0,0);
        //assertion block
        Assert.assertNotNull(lpActivity.findViewById(R.id.lvl1_btn));
        Button button = new Button(lpActivity);
        button.setText("1");
        lpActivity.goLvl(button);
        Activity levelInfoActivity = InstrumentationRegistry.getInstrumentation()
                .waitForMonitorWithTimeout(levelInfoMonitor,5000);
        TextView tv = levelInfoActivity.findViewById(R.id.statusText);
        Assert.assertEquals(tv.getText(),"Пройдено");
        //remove data
        db.updateUserStat(res.getInt(0),res.getInt(1),res.getInt(2),res.getInt(3));
        levelInfoActivity.finish();
    }
    @Test
    public void levelPickActivityMapButtonClickUITest() {
        Assert.assertNotNull(lpActivity.findViewById(R.id.map_button));
        Button button = new Button(lpActivity);
        button.setText("Карта");
        lpActivity.goLvl(button);
        Activity levelInfoActivity = InstrumentationRegistry.getInstrumentation()
                .waitForMonitorWithTimeout(levelInfoMonitor,5000);
        Assert.assertNotNull(levelInfoActivity);
        levelInfoActivity.finish();
    }
    @Test
    public void levelPickActivityInfinityButtonClickUITest() {
        Assert.assertNotNull(lpActivity.findViewById(R.id.infinity_btn));
        Button button = new Button(lpActivity);
        lpActivity.goLevel(button);
        Activity infinityLevelActivity = InstrumentationRegistry.getInstrumentation()
                .waitForMonitorWithTimeout(levelMonitor,5000);
        Assert.assertNotNull(infinityLevelActivity);
        infinityLevelActivity.finish();
    }
    @After
    public void tearDown() throws Exception{
        lpActivity = null;
    }
}
