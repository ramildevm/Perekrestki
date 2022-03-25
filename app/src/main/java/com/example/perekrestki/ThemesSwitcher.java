package com.example.perekrestki;

import android.app.Instrumentation;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import androidx.appcompat.app.AppCompatActivity;


public class ThemesSwitcher {
    public static int layoutBackColor = Color.parseColor("#513215");
    public static int mainColor = Color.parseColor("#FDB912");
    public static int backColor = 0;
    public static void switchMain(String color){
        mainColor = Color.parseColor(color);
    }
    public static void switchLayoutBack(String color){
        layoutBackColor = Color.parseColor(color);
    }
    public static void switchBack(int colorId){
        backColor = colorId;
    }
}
