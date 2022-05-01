package com.example.perekrestki;

import static org.junit.Assert.assertEquals;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class DBHelperTest extends TestCase {
    Context appContext;
    @Before
    public void setUp() throws Exception{
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }
    @Test
    public void create_dbThrowsExceptionTest() {
        String actualDbName = DBHelper.getDbName();
        try {
        DBHelper db = new DBHelper(appContext);
        DBHelper.setDbName("GameDB.db");
        db.create_db();
        }
        catch (IOException thrown){
            Assert.assertNotEquals("",thrown.getMessage());
        }
        DBHelper.setDbName(actualDbName);
    }
    @Test
    public void openDbWithWrongPathTest(){
        DBHelper db = null;
        String actualDbPath = DBHelper.getDbPath();
        try {
            db = getNewDatabase();
        } catch (IOException e) {
            Assert.fail();
            return;
        }
        DBHelper.setDbPath("gameDb.db");
        try {
            db.open();
        }
        catch (SQLException thrown){
            Assert.assertNotEquals("",thrown.getMessage());
        }
        DBHelper.setDbPath(actualDbPath);
    }
    @Test
    public void updateUserSettingsWithWrongIdTest() {
        DBHelper db = null;
        try {
            db = getNewDatabase();
        } catch (IOException e) {
            Assert.fail(e.getMessage());
            return;
        }
        Boolean actual = db.updateUserSettings(12,"#000","#fff");
        Assert.assertEquals(false,actual);
    }
    @Test
    public void updateUserStatsWithWrongIdTest() {
        DBHelper db = null;
        try {
            db = getNewDatabase();
        } catch (IOException e) {
            Assert.fail();
            return;
        }
        Boolean actual = db.updateUserStat(23,0,0,0);
        Assert.assertFalse(actual);
    }
    @Test
    public void getScenesByIdTest() {
        DBHelper db = null;
        try {
            db = getNewDatabase();
        } catch (IOException e) {
            Assert.fail();
            return;
        }
        Cursor res = db.getLevelScene(1);
        Assert.assertEquals(3,res.getCount());
    }
    @Test
    public void getHardesLevelTest() {
        DBHelper db = null;
        try {
            db = getNewDatabase();
        } catch (IOException e) {
            Assert.fail();
            return;
        }
        Cursor res = db.getMaxLevel();
        Assert.assertEquals(13,res.getCount());
    }
    @Test
    public void insertExictingLevelTest() {
        DBHelper db = null;
        try {
            db = getNewDatabase();
        } catch (IOException e) {
            Assert.fail();
            return;
        }
        Boolean res = db.insertLevel(1,0,3,"Hard");
        Assert.assertFalse(res);
    }
    private DBHelper getNewDatabase() throws IOException{
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DBHelper db = new DBHelper(appContext);
        db.create_db();
        return db;
    }

    @Test
    public void insertExictingUserSettingsTest() {
        DBHelper db = null;
        try {
            db = getNewDatabase();
        } catch (IOException e) {
            Assert.fail();
            return;
        }
        Boolean res = db.insertUserSettings(1,"#fff","#000");
        Assert.assertFalse(res);
    }

    @Test
    public void insertExictingUserStatTest() {
        DBHelper db = null;
        try {
            db = getNewDatabase();
        } catch (IOException e) {
            Assert.fail();
            return;
        }
        Boolean res = db.insertUserStat(1,0,0,0);
        Assert.assertFalse(res);
    }

    @Test
    public void insertExictingSceneTest() {
        DBHelper db = null;
        try {
            db = getNewDatabase();
        } catch (IOException e) {
            Assert.fail();
            return;
        }
        Boolean res = db.insertScene(1,0,0,0,0,0,"Yes","Mo","No",0);
        Assert.assertFalse(res);
    }

    @Test
    public void insertExictingLevelSceneTest() {
        DBHelper db = null;
        try {
            db = getNewDatabase();
        } catch (IOException e) {
            Assert.fail();
            return;
        }
        Boolean res = db.insertLevelScene(1,1,1,0);
        Assert.assertFalse(res);
    }
}