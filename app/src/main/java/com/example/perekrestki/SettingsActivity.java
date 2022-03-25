package com.example.perekrestki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RadioButton;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        findViewById(R.id.mainBack).setBackgroundColor(ThemesSwitcher.layoutBackColor);
        if(ThemesSwitcher.mainColor==Color.parseColor("#00FF38"))
            findViewById(R.id.colorBtn).setBackgroundColor(ThemesSwitcher.mainColor);
        if(ThemesSwitcher.layoutBackColor==Color.parseColor("#525252"))
            findViewById(R.id.colorBackBtn).setBackgroundColor(Color.parseColor("#757575"));
    }
    public void goStatPage(View view) {
        startActivity(new Intent(SettingsActivity.this,UserStatActivity.class));
    }

    public void goMain(View view) {
        startActivity(new Intent(SettingsActivity.this,MainActivity.class));
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    public void changeColor(View view) {
        int orange = Color.parseColor("#FDB912"), green =   Color.parseColor("#00FF38");
        if(ThemesSwitcher.mainColor == green) {
            ThemesSwitcher.switchMain("#FDB912");
            ThemesSwitcher.switchBack(R.drawable.rounded_border_complete_y);
        }
        else if(ThemesSwitcher.mainColor == orange){
            ThemesSwitcher.switchMain("#00FF38");
            ThemesSwitcher.switchBack(R.drawable.rounded_border_complete_g);
        }
        view.setBackgroundColor(ThemesSwitcher.mainColor);
    }

    public void changeBackColor(View view) {
        int brown = Color.parseColor("#513215"), gray = Color.parseColor("#525252");
        if(ThemesSwitcher.layoutBackColor == gray) {
            ThemesSwitcher.switchLayoutBack("#513215");
            view.setBackgroundColor(Color.parseColor("#653215"));
        }
        else if(ThemesSwitcher.layoutBackColor == brown){
            ThemesSwitcher.switchLayoutBack("#525252");
            view.setBackgroundColor(Color.parseColor("#757575"));
        }
        findViewById(R.id.mainBack).setBackgroundColor(ThemesSwitcher.layoutBackColor);
    }
}