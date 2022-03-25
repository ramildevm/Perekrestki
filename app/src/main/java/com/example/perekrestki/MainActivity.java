package com.example.perekrestki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    DBHelper db;
    Boolean check = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);
        createData();
        if(ThemesSwitcher.backColor == null){
            ThemesSwitcher.switchBack(ContextCompat.getDrawable(MainActivity.this,R.drawable.rounded_border_complete_y));
        }
        setViewTheme();
    }

    private void setViewTheme() {
        View b = findViewById(R.id.start_button);
        b.setBackground(ThemesSwitcher.backColor);
    }

    void createData(){
        if(db.getuserstat().getCount()==0)
            check = db.insertuserstat(1,0,0,0);
        if(db.getlevels().getCount()==0) {
            db.insertlevel(0, 0, 0, "NO_DATA");
            db.insertlevel(1, 0, 3, "Легко");
            db.insertlevel(2, 0, 3, "Легко");
            db.insertlevel(3, 0, 4, "Легко");
            db.insertlevel(4, 0, 4, "Легко");
            db.insertlevel(5, 0, 4, "Легко");
            db.insertlevel(6, 0, 4, "Легко");
            db.insertlevel(7, 0, 4, "Легко");
            db.insertlevel(8, 0, 5, "Средне");
            db.insertlevel(9, 0, 3, "Легко");
            db.insertlevel(10, 0, 6, "Средне");
            db.insertlevel(11, 0, 6, "Средне");
            db.insertlevel(12, 0, 6, "Средне");
        }
        if(db.getscenes().getCount()==0){
            db.insertscene(1,R.layout.scene13_1,R.id.tran0,R.xml.scene13_1_scene,R.xml.scene13_1_scene,R.xml.scene13_1_scene,"Красный, желтый, зеленый","Красный, зеленый,желтый","Желтый, красный, зеленый",R.id.motion_layout13_1);
            db.insertscene(2,R.layout.scene14_1,R.id.tran0,R.xml.scene14_1_scene,R.xml.scene14_1_scene,R.xml.scene14_1_scene,"Зеленый, фиолетовый","Зеленый и фиолетовый","Фиолетовый, зеленый",R.id.motion_layout14_1);
            db.insertscene(3,R.layout.scene7_1,R.id.tran0,R.xml.scene7_1_scene,R.xml.scene7_1_scene,R.xml.scene7_1_scene,"Зеленый, красный","Зеленый и красный","Красный, зеленый",R.id.motion_layout7_1);
            db.insertscene(4,R.layout.scene13_2,R.id.tran0,R.xml.scene13_2_scene,0,0,"Фиолетовый, синий, красный","Фиолетовый, синий, красный","Красный, фиолетовый, синий",R.id.motion_layout13_2);
            db.insertscene(5,R.layout.scene12_2,R.id.tran0,R.xml.scene12_2_scene,0,0,"Красный, синий, зеленый","Зеленый, синий, красный","Красный, зеленый, синий",R.id.motion_layout12_2);
            db.insertscene(6,R.layout.scene4_2,R.id.tran0,R.xml.scene4_2_scene,0,0,"Поезд, красный и зеленый","Красный, поезд, зеленый","Зеленый, поезд, красный",R.id.motion_layout4_2);
        }
        if(db.getlevelscenes().getCount() ==0){
            db.insertlevelscene(1,1,1,1);
            db.insertlevelscene(2,1,2,2);
            db.insertlevelscene(3,1,3,3);
            db.insertlevelscene(4,2,4,1);
            db.insertlevelscene(5,2,5,2);
            db.insertlevelscene(6,2,6,3);
        }
    }
    public void goLvlPick(View view) {
        startActivity(new Intent(MainActivity.this,LevelPickActivity.class));
    }

    public void goPddPage(View view) {
        startActivity(new Intent(MainActivity.this,PddPageActivity.class));
    }
    public void goSettingsPage(View view) {
        startActivity(new Intent(MainActivity.this,SettingsActivity.class));
    }
}