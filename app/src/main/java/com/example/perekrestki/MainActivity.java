package com.example.perekrestki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
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
        setViewTheme();
    }

    private void setViewTheme() {
        Cursor res = db.getusersettings();
        res.moveToFirst();
        ThemesSwitcher.switchLayoutBack(res.getString(2));
        ThemesSwitcher.switchMain(res.getString(1));
        if(ThemesSwitcher.mainColor == Color.parseColor("#FDB912"))
            ThemesSwitcher.switchBack(R.drawable.rounded_border_complete_y);
        else if (ThemesSwitcher.mainColor == Color.parseColor("#00FF38"))
            ThemesSwitcher.switchBack(R.drawable.rounded_border_complete_g);
        findViewById(R.id.mainBack).setBackgroundColor(ThemesSwitcher.layoutBackColor);
        View b = findViewById(R.id.start_button);
        b.setBackground(getResources().getDrawable(ThemesSwitcher.backColor));
    }

    void createData(){
        if(db.getuserstat().getCount()==0)
            check = db.insertuserstat(1,0,0,0);
        if(db.getusersettings().getCount()==0)
            check = db.insertusersettings(1,"#FDB912","#513215");
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
            db.insertscene(2,R.layout.scene14_1,R.id.tran0,R.xml.scene14_1_scene,R.xml.scene14_1_scene,R.xml.scene14_1_scene,"Зеленый, фиолетовый","Зеленый И фиолетовый","Фиолетовый, зеленый",R.id.motion_layout14_1);
            db.insertscene(3,R.layout.scene7_1,R.id.tran0,R.xml.scene7_1_scene,R.xml.scene7_1_scene,R.xml.scene7_1_scene,"Зеленый, красный","Зеленый И красный","Красный, зеленый",R.id.motion_layout7_1);
            db.insertscene(4,R.layout.scene13_2,R.id.tran0,R.xml.scene13_2_scene,0,0,"Фиолетовый, синий, красный","Фиолетовый, красный, синий","Красный, фиолетовый, синий",R.id.motion_layout13_2);
            db.insertscene(5,R.layout.scene12_2,R.id.tran0,R.xml.scene12_2_scene,0,0,"Красный, синий, зеленый","Зеленый, синий, красный","Красный, зеленый, синий",R.id.motion_layout12_2);
            db.insertscene(6,R.layout.scene4_2,R.id.tran0,R.xml.scene4_2_scene,0,0,"Поезд, красный И зеленый","Красный, поезд, зеленый","Зеленый, поезд, красный",R.id.motion_layout4_2);
            db.insertscene(7,R.layout.scene13_3,R.id.tran0,R.xml.scene13_3_scene,0,0,"Фиолетовый, зеленый, черный","Фиолетовый, черный, зеленый","Зеленый, черный, фиолетовый",R.id.motion_layout13_3);
            db.insertscene(8,R.layout.scene12_3,R.id.tran0,R.xml.scene12_3_scene,0,0,"Черный, желтый","Желтый, черный","Желтый И черный",R.id.motion_layout12_3);
            db.insertscene(9,R.layout.scene9_3,R.id.tran0,R.xml.scene9_3_scene,0,0,"Желтый, черный","Черный, желтый","Желтый И черный",R.id.motion_layout9_3);
            db.insertscene(10,R.layout.scene5_3,R.id.tran0,R.xml.scene5_3_scene,0,0,"Черный, красный И синий","Синий, черный, красный","Синий и красный, черный",R.id.motion_layout5_3);

        }
        if(db.getlevelscenes().getCount() ==0){
            db.insertlevelscene(1,1,1,1);
            db.insertlevelscene(2,1,2,2);
            db.insertlevelscene(3,1,3,3);
            db.insertlevelscene(4,2,4,1);
            db.insertlevelscene(5,2,5,2);
            db.insertlevelscene(6,2,6,3);
            db.insertlevelscene(7,3,7,1);
            db.insertlevelscene(8,3,8,2);
            db.insertlevelscene(9,3,9,3);
            db.insertlevelscene(10,3,10,4);
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