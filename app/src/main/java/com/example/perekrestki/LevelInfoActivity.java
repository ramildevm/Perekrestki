package com.example.perekrestki;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LevelInfoActivity extends AppCompatActivity {
private String LevelNum;
    int imgId;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_info);
        db = new DBHelper(this);
        LevelNum = getIntent().getStringExtra("Number");
        if(!LevelNum.isEmpty())
            setData(Integer.parseInt(LevelNum));
        ImageView img = findViewById(R.id.map_img);
        switch (LevelNum){
            case "1":
                imgId = R.drawable.level1;
                break;
            case "2":
                imgId =(R.drawable.level2);
                break;
            case "3":
                imgId =(R.drawable.level3);
                break;
            case "4":
                imgId =(R.drawable.level4);
                break;
            case "5":
                imgId =(R.drawable.level5);
                break;
            case "6":
                imgId =(R.drawable.level6);
                break;
            case "7":
                imgId =(R.drawable.level7);
                break;
            case "8":
                imgId =(R.drawable.level8);
                break;
            case "9":
                imgId =(R.drawable.level9);
                break;
            case "10":
                imgId =(R.drawable.level10);
                break;
            case "11":
                imgId =(R.drawable.level11);
                break;
            case "12":
                imgId =(R.drawable.level12);
                break;
            default:
                imgId =(R.drawable.level_map);
                break;
        }
        img.setImageResource(imgId);

        TextView txtLvl = (TextView) findViewById(R.id.textViewLvl);
        if(!LevelNum.isEmpty()) {
            txtLvl.setText("Уровень " + LevelNum);
        }
        else {
            txtLvl.setText("Карта");
            findViewById(R.id.start_button).setVisibility(View.INVISIBLE);
            findViewById(R.id.difficult).setVisibility(View.INVISIBLE);
            findViewById(R.id.fails).setVisibility(View.INVISIBLE);
            findViewById(R.id.scenesNum).setVisibility(View.INVISIBLE);
        }
        findViewById(R.id.map_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(LevelInfoActivity.this,findViewById(R.id.map_img),"map_img").toBundle();
                    startActivity(new Intent(LevelInfoActivity.this,MapActivity.class).putExtra("text",txtLvl.getText()).putExtra("Number",imgId),bundle);
                }
            }
        });
    }

    private void setData(int levelNum) {
        Cursor level = db.getlevel(levelNum);
        while(level.moveToNext()){
            ((TextView)findViewById(R.id.difficultText)).setText(level.getString(3));
            ((TextView)findViewById(R.id.scenesNumText)).setText(""+level.getInt(2));
            ((TextView)findViewById(R.id.failsText)).setText(""+level.getInt(1));
        }
    }

    public void goBack(View view) {
        onBackPressed();
    }

    public void startLvl(View view) {
    }
}