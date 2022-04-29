package com.example.perekrestki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(MainActivity.this);
        // создаем базу данных

        try {
            db.create_db();
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateSceneData(db);
        setViewTheme();
    }
    public View setViewTheme() {
        ThemesSwitcher.updateDataFromDb(db);
        findViewById(R.id.mainBack).setBackgroundColor(ThemesSwitcher.layoutBackColor);
        View b = findViewById(R.id.start_button);
        b.setBackground(getResources().getDrawable(ThemesSwitcher.backColor));
        return b;
    }
    public Boolean updateSceneData(DBHelper _db){
        Boolean result = true;
        result = _db.updateScene(1,R.layout.scene13_1,R.id.tran0,R.xml.scene13_1_scene,R.xml.scene13_1_scene,R.xml.scene13_1_scene,"Желтый, красный, зеленый","Красный, зеленый,желтый","Красный, желтый, зеленый",R.id.motion_layout13_1);
        result = _db.updateScene(2,R.layout.scene14_1,R.id.tran0,R.xml.scene14_1_scene,R.xml.scene14_1_scene,R.xml.scene14_1_scene,"Зеленый, фиолетовый","Зеленый И фиолетовый","Фиолетовый, зеленый",R.id.motion_layout14_1);
        result = _db.updateScene(3,R.layout.scene7_1,R.id.tran0,R.xml.scene7_1_scene,R.xml.scene7_1_scene,R.xml.scene7_1_scene,"Зеленый, красный","Зеленый И красный","Красный, зеленый",R.id.motion_layout7_1);
        result = _db.updateScene(4,R.layout.scene13_2,R.id.tran0,R.xml.scene13_2_scene,0,0,"Фиолетовый, синий, красный","Фиолетовый, красный, синий","Красный, фиолетовый, синий",R.id.motion_layout13_2);
        result = _db.updateScene(5,R.layout.scene12_2,R.id.tran0,R.xml.scene12_2_scene,0,0,"Красный, синий, зеленый","Зеленый, синий, красный","Красный, зеленый, синий",R.id.motion_layout12_2);
        result = _db.updateScene(6,R.layout.scene4_2,R.id.tran0,R.xml.scene4_2_scene,0,0,"Поезд, красный И зеленый","Красный, поезд, зеленый","Зеленый, поезд, красный",R.id.motion_layout4_2);
        result = _db.updateScene(7,R.layout.scene13_3,R.id.tran0,R.xml.scene13_3_scene,0,0,"Фиолетовый, зеленый, черный","Фиолетовый, черный, зеленый","Зеленый, черный, фиолетовый",R.id.motion_layout13_3);
        result = _db.updateScene(8,R.layout.scene12_3,R.id.tran0,R.xml.scene12_3_scene,0,0,"Черный, желтый","Желтый, черный","Желтый И черный",R.id.motion_layout12_3);
        result = _db.updateScene(9,R.layout.scene9_3,R.id.tran0,R.xml.scene9_3_scene,0,0,"Желтый, черный","Черный, желтый","Желтый И черный",R.id.motion_layout9_3);
        result = _db.updateScene(10,R.layout.scene5_3,R.id.tran0,R.xml.scene5_3_scene,0,0,"Черный, красный И синий","Синий, черный, красный","Синий и красный, черный",R.id.motion_layout5_3);
        return result;
    }
    public void goLvlPick(View view) {
        startActivity(new Intent(MainActivity.this,LevelPickActivity.class));
    }

    public void goPddPage(View view) throws InterruptedException {
        startActivity(new Intent(MainActivity.this,PddPageActivity.class));
    }

    public void goSettingsPage(View view) {
        startActivity(new Intent(MainActivity.this,SettingsActivity.class));
    }
}