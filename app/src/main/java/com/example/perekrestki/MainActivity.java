package com.example.perekrestki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBHelper db;
    Boolean check = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);
        createData();
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
            db.insertscene(2,R.layout.scene14_1,R.id.tran0,R.xml.scene14_1_scene,R.xml.scene14_1_scene,R.xml.scene14_1_scene,"Зеленый, фиолетовый","Зеленый и фиолетовый","Фмолетовый, зеленый",R.id.motion_layout14_1);
            db.insertscene(3,R.layout.scene7_1,R.id.tran0,R.xml.scene7_1_scene,R.xml.scene7_1_scene,R.xml.scene7_1_scene,"Зеленый, красный","Зеленый и красный","Красный, зеленый",R.id.motion_layout7_1);
        }
        if(db.getlevelscenes().getCount() ==0){
            db.insertlevelscene(1,1,1,1);
            db.insertlevelscene(2,1,2,2);
            db.insertlevelscene(3,1,3,3);
        }

    }
    public void goLvlPick(View view) {
        startActivity(new Intent(MainActivity.this,LevelPickActivity.class));
    }

    public void goPddPage(View view) {
        startActivity(new Intent(MainActivity.this,PddPageActivity.class));
    }

    public void goStatPage(View view) {
        startActivity(new Intent(MainActivity.this,UserStatActivity.class));
    }
}