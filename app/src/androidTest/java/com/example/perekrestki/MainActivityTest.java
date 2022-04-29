package com.example.perekrestki;

import static org.junit.Assert.*;

import android.app.Instrumentation;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends TestCase {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Test
    public void updateSceneDataTest() {
        MainActivity ma = mActivityTestRule.getActivity();
        Boolean actual = ma.updateSceneData(new DBHelper(ma));
        assertTrue(actual);
    }
    @Test
    public void setViewThemeTest() {
        MainActivity ma = mActivityTestRule.getActivity();
        View button = ma.findViewById(R.id.start_button);
        View actual = ma.setViewTheme();
        assertSame(button, actual);
    }
    @Test
    public void useAppContextText() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.perekrestki", appContext.getPackageName());
    }
}