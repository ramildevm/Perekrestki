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
        db.execSQL("create table Scenes(id INTEGER primary key, layout INTEGER, transition INTEGER, correctMS INTEGER, secondMS INTEGER, " +
                    "thirdMS INTEGER, correct TEXT, second TEXT, third TEXT,idML INTEGER)");
        db.execSQL("create table LevelScene(id INTEGER primary key, idlvl INTEGER not null, idscene INTEGER not null,priority INTEGER," +
                    "foreign key (idlvl) references Levels(num),foreign key (idscene) references Scenes(id))");
        //createData(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists UserStat");
        db.execSQL("drop Table if exists Levels");
        db.execSQL("drop Table if exists Scenes");
        db.execSQL("drop Table if exists LevelScene");
    }
    //*********************************************************************************************
    //UserData Contex
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
        cv.put("lvlcount",lvlcount);
        cv.put("fails",fails);
        cv.put("hardlvl",hardlvl);
        long result = db.update("UserStat",cv,"id=?", new String[]{""+id});
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
    //*********************************************************************************************
    //Levels Context
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
    public Boolean updatelevel(int num,int fails){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from Levels where num=?",new String[]{""+num});
        res.moveToNext();

        ContentValues cv = new ContentValues();
        cv.put("num",num);
        cv.put("fails",fails);
        cv.put("scenes",res.getInt(2));
        cv.put("difficulty",res.getString(3));
        long result = db.update("Levels",cv,"num=?", new String[]{""+num});
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

    public Cursor getmaxlevel() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Levels order by fails desc",null);
        return cursor;
    }
    //*********************************************************************************************
    //Scenes Context
    public Boolean insertscene(int id,int layout, int transition,int correctMS,int secondMS,
                               int thirdMS,String correct,String second,String third,int idML){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",id);
        cv.put("layout",layout);
        cv.put("transition",transition);
        cv.put("correctMS",correctMS);
        cv.put("secondMS",secondMS);
        cv.put("thirdMS",thirdMS);
        cv.put("correct",correct);
        cv.put("second",second);
        cv.put("third",third);
        cv.put("idML",idML);
        long result = db.insert("Scenes",null,cv);
        if (result ==-1)
            return false;
        else
            return true;
    }
    public Cursor getscenes(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Scenes",null);
        return cursor;
    }
    public Cursor getscene(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Scenes where id=?", new String[]{"" + id});
        return cursor;
    }
    //*********************************************************************************************
    //LevelScene Contex
    public Boolean insertlevelscene(int id,int lvlid, int sceneid,int priority){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",id);
        cv.put("idlvl",lvlid);
        cv.put("idscene",sceneid);
        cv.put("priority",priority);
        long result = db.insert("LevelScene",null,cv);
        if (result ==-1)
            return false;
        else
            return true;
    }
    public Cursor getlevelscenes(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from LevelScene",null);
        return cursor;
    }
    public Cursor getlevelscene(int lvlid){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from LevelScene where idlvl=? order by priority",new String[]{""+lvlid});
        return cursor;
    }
}
