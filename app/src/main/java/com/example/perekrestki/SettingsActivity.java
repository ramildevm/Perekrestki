package com.example.perekrestki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        db = new DBHelper(this);
        findViewById(R.id.mainBack).setBackgroundColor(ThemesSwitcher.layoutBackColor);
        if(ThemesSwitcher.mainColor==Color.parseColor("#00FF38"))
            findViewById(R.id.colorBtn).setBackgroundColor(ThemesSwitcher.mainColor);
        if(ThemesSwitcher.layoutBackColor==Color.parseColor("#525252"))
            findViewById(R.id.colorBackBtn).setBackgroundColor(Color.parseColor("#757575"));
    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(SettingsActivity.this,MainActivity.class));
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
    public void goStatPage(View view) {
        startActivity(new Intent(SettingsActivity.this,UserStatActivity.class));
    }

    public void goMain(View view) {
        startActivity(new Intent(SettingsActivity.this,MainActivity.class));
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    public void changeColor(View view) {
        String orange = "#FDB912", green = "#00FF38";
        Cursor res = db.getUserSettings();
        res.moveToFirst();
        if(res.getString(1).equals(green)) {
            db.updateUserSettings(1,"#FDB912",res.getString(2));
            ThemesSwitcher.switchBack(R.drawable.rounded_border_complete_y);
            view.setBackgroundColor(Color.parseColor("#FDB912"));
        }
        else if(res.getString(1).equals(orange)){
            db.updateUserSettings(1,"#00FF38",res.getString(2));
            ThemesSwitcher.switchBack(R.drawable.rounded_border_complete_g);
            view.setBackgroundColor(Color.parseColor("#00FF38"));
        }
    }

    public void changeBackColor(View view) {
        String brown = "#513215", gray = "#525252";
        Cursor res = db.getUserSettings();
        res.moveToFirst();
        if(res.getString(2).equals(gray)) {
            db.updateUserSettings(1,res.getString(1),"#513215");
            view.setBackgroundColor(Color.parseColor("#653215"));
            findViewById(R.id.mainBack).setBackgroundColor(Color.parseColor("#513215"));
        }
        else if(res.getString(2).equals(brown)){
            db.updateUserSettings(1,res.getString(1),"#525252");
            view.setBackgroundColor(Color.parseColor("#757575"));
            findViewById(R.id.mainBack).setBackgroundColor(Color.parseColor("#525252"));
        }
    }
}