package com.example.perekrestki;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
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
public class UserStatActivityTest {
    @Rule
    public ActivityTestRule<UserStatActivity> usActivityTestRule = new ActivityTestRule<>(UserStatActivity.class);

    private UserStatActivity usActivity= null;
    @Before
    public void setUp() throws Exception{
        usActivity = usActivityTestRule.getActivity();
    }
    @Test
    public void levelPickActivitySetViewThemeMethodTest(){
        //get & update data from db
        DBHelper db = new DBHelper(usActivityTestRule.getActivity());
        Cursor res = db.getUserStat();
        res.moveToFirst();
        Cursor hardlvlres = db.getMaxLevel();
        hardlvlres.moveToFirst();
        db.updateUserStat(1,2,4,0);
        //call method
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    usActivity.setData();
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            Assert.fail(throwable.getMessage());
        }
        //return data
        db.updateUserStat(res.getInt(0),res.getInt(1),res.getInt(2),
                res.getInt(3));
        //assertion block
        TextView lvlcountText = usActivity.findViewById(R.id.lvlcountText);
        TextView failsText = usActivity.findViewById(R.id.failsText);
        TextView hardlvlText = usActivity.findViewById(R.id.hardlvlText);
        TextView hardlvlfailsText = usActivity.findViewById(R.id.hardlvlfailsText);
        Assert.assertEquals("2",lvlcountText.getText());
        Assert.assertEquals("4",failsText.getText());
        Assert.assertEquals(""+hardlvlres.getInt(0),hardlvlText.getText());
        Assert.assertEquals(""+ hardlvlres.getInt(1),hardlvlfailsText.getText());
        }
    @After
    public void tearDown() throws Exception{
        usActivity = null;
    }
}
