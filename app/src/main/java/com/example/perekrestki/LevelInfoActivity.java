package com.example.perekrestki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.NavUtils;
import androidx.core.content.ContextCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LevelInfoActivity extends AppCompatActivity {
    private String LevelNum;
    int imgId;
    DBHelper db;
    @Override
    public void onBackPressed(){
        NavUtils.navigateUpFromSameTask(this);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_info);
        setViewTheme();
        db = new DBHelper(this);
        LevelNum = getIntent().getStringExtra("Number");
        Boolean isAvaible = getIntent().getBooleanExtra("isAvaible", false);
        if(!LevelNum.equals(getString(R.string.button_map)))
            setData(Integer.parseInt(LevelNum));
        else{
            ConstraintLayout constraintLayout = findViewById(R.id.mainBack);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(R.id.map_img,ConstraintSet.TOP,R.id.mainBack,ConstraintSet.TOP,0);
            constraintSet.connect(R.id.map_img,ConstraintSet.BOTTOM,R.id.mainBack,ConstraintSet.BOTTOM,0);
            constraintSet.connect(R.id.map_img,ConstraintSet.START,R.id.mainBack,ConstraintSet.START,0);
            constraintSet.connect(R.id.map_img,ConstraintSet.END,R.id.mainBack,ConstraintSet.END,0);
            constraintSet.applyTo(constraintLayout);
        }

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
        if(!LevelNum.equals(getString(R.string.button_map))) {
            txtLvl.setText("Уровень " + LevelNum);
            if (isAvaible)
                findViewById(R.id.start_button).setVisibility(View.VISIBLE);
        }
        else {
            txtLvl.setText("Карта");
            findViewById(R.id.start_button).setVisibility(View.INVISIBLE);
            findViewById(R.id.difficult).setVisibility(View.INVISIBLE);
            findViewById(R.id.fails).setVisibility(View.INVISIBLE);
            findViewById(R.id.scenesNum).setVisibility(View.INVISIBLE);
            findViewById(R.id.status).setVisibility(View.INVISIBLE);
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

    private void setViewTheme() {
        findViewById(R.id.mainBack).setBackgroundColor(ThemesSwitcher.layoutBackColor);
        ((TextView)findViewById(R.id.difficultText)).setTextColor(ThemesSwitcher.mainColor);
        ((TextView)findViewById(R.id.failsText)).setTextColor(ThemesSwitcher.mainColor);
        ((TextView)findViewById(R.id.scenesNumText)).setTextColor(ThemesSwitcher.mainColor);
        findViewById(R.id.start_button).setBackground(ContextCompat.getDrawable(this,ThemesSwitcher.backColor));
    }

    private void setData(int levelNum) {
        Cursor level = db.getlevel(levelNum);
        while(level.moveToNext()){
            ((TextView)findViewById(R.id.difficultText)).setText(level.getString(3));
            ((TextView)findViewById(R.id.scenesNumText)).setText(""+level.getInt(2));
            ((TextView)findViewById(R.id.failsText)).setText(""+level.getInt(1));
        }
        Cursor res = db.getuserstat();
        res.moveToNext();
        if(res.getInt(1)>=levelNum){
            ((TextView)findViewById(R.id.statusText)).setText("Пройдено");
            ((TextView)findViewById(R.id.statusText)).setTextColor(0xff6aa644);
        }
        else{
            ((TextView)findViewById(R.id.statusText)).setText("Не пройдено");
            ((TextView)findViewById(R.id.statusText)).setTextColor(0xff888888);
        }
    }

    public void goBack(View view) {
        NavUtils.navigateUpFromSameTask(this);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    public void startLvl(View view) {
        if (Integer.parseInt(LevelNum) > 3){
            Toast.makeText(this, "Пока что в разработке!", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(new Intent(this,LevelActivity.class).putExtra("Number",Integer.parseInt(LevelNum)));
    }
}