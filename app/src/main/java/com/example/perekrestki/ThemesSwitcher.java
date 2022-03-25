package com.example.perekrestki;

import android.app.Instrumentation;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import androidx.appcompat.app.AppCompatActivity;


public class ThemesSwitcher {
    public static int textColor = Color.parseColor("#00FF38");
    public static Drawable backColor;
    public static void switchText(String color){
        textColor = Color.parseColor(color);
    }
    public static void switchBack(Drawable color){
        backColor = color;
    }
}
