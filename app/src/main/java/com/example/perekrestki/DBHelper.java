package com.example.perekrestki;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "Gamedata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table UserStat(id INTEGER primary key, lvlcount INTEGER, fails INTEGER, hardlvl INTEGER)");
        db.execSQL("create table Levels(num INTEGER primary key, fails INTEGER, scenes INTEGER,difficulty TEXT)");
        //db.execSQL("create table Scenes(name INTEGER primary key, fails INTEGER, scenes INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists UserStat");
        db.execSQL("drop Table if exists Levels");
    }
    public Boolean insertuserstat(int id,int lvlcount, int fails,int hardlvl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",id);
        cv.put("lvlcount",lvlcount);
        cv.put("fails",fails);
        cv.put("hardlvl",hardlvl);
        long result = db.insert("UserStat",null,cv);
        if (result ==-1)
            return false;
        else
            return true;
    }
    public Boolean updateuserstat(int id, int lvlcount, int fails,int hardlvl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("lvlount",lvlcount);
        cv.put("fails",fails);
        cv.put("hardlvl",hardlvl);
        long result = db.update("UserStat",cv,"where id=?", new String[]{""+id});
        if (result ==-1)
            return false;
        else {
            return true;
        }
    }
    public Cursor getuserstat(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from UserStat where id=1",null);
        return cursor;
    }
    public Boolean insertlevel(int num,int fails, int scenes,String difficulty){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("num",num);
        cv.put("fails",fails);
        cv.put("scenes",scenes);
        cv.put("difficulty",difficulty);
        long result = db.insert("Levels",null,cv);
        if (result ==-1)
            return false;
        else
            return true;
    }
    public Cursor getlevels(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Levels",null);
        return cursor;
    }
    public Cursor getlevel(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Levels where num=?",new String[]{""+id});
        return cursor;
    }
}
