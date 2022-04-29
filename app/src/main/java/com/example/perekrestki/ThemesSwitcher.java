package com.example.perekrestki;

import android.app.Instrumentation;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import androidx.appcompat.app.AppCompatActivity;


public class ThemesSwitcher {
    public static int layoutBackColor = Color.parseColor("#513215");
    public static int mainColor = Color.parseColor("#FDB912");
    public static int backColor = R.drawable.rounded_border_complete_y;
    public static void switchMain(String color){
        mainColor = Color.parseColor(color);
    }
    public static void switchLayoutBack(String color){
        layoutBackColor = Color.parseColor(color);
    }
    public static void switchBack(int colorId){
        backColor = colorId;
    }
    public static void updateDataFromDb(DBHelper db){
        Cursor res = db.getUserSettings();
        res.moveToFirst();
        ThemesSwitcher.switchLayoutBack(res.getString(2));
        ThemesSwitcher.switchMain(res.getString(1));
        if(ThemesSwitcher.mainColor == Color.parseColor("#FDB912"))
            ThemesSwitcher.switchBack(R.drawable.rounded_border_complete_y);
        else if (ThemesSwitcher.mainColor == Color.parseColor("#00FF38"))
            ThemesSwitcher.switchBack(R.drawable.rounded_border_complete_g);
    }
}
