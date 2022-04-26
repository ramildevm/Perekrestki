package com.example.perekrestki;

import static org.junit.Assert.*;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class DBHelperTest extends TestCase {
    @Test
    public void create_dbThrowsExceptionTest() {
        try {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DBHelper db = new DBHelper(appContext);
        db.setDbName("GameDB.db");
        db.create_db();
        }
        catch (IOException thrown){
            Assert.assertNotEquals("",thrown.getMessage());
        }
    }
    @Test
    public void openDbWithWrongPathTest(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DBHelper db = new DBHelper(appContext);
        try {
            db.create_db();
        } catch (IOException e) {
            Assert.assertNotEquals("",e.getMessage());
        }
        db.setDbPath("gameDb.db");
        try {
            db.open();
        }
        catch (SQLException thrown){
            Assert.assertNotEquals("",thrown.getMessage());
        }
    }
    @Test
    public void updateUserSettingsWithWrongIdTest() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DBHelper db = new DBHelper(appContext);
        try {
            db.create_db();
        } catch (IOException e) {
            Assert.assertNotEquals("",e.getMessage());
        }
        Boolean actual = db.updateusersettings(12,"#000","#fff");
        Assert.assertEquals(false,actual);
    }
    @Test
    public void updateUserStatsWithWrongIdTest() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DBHelper db = new DBHelper(appContext);
        try {
            db.create_db();
        } catch (IOException e) {
            Assert.assertNotEquals("",e.getMessage());
        }
        Boolean actual = db.updateuserstat(2,0,0,0);
        Assert.assertEquals(false,actual);
    }
}