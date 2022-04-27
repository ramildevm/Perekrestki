package com.example.perekrestki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NavUtils;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class LevelPickActivity extends AppCompatActivity {
    DBHelper db;
    private int comppletedLvlCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_pick);
        setViewTheme();
        db = new DBHelper(this);
        checkButtons();
    }
    private void setViewTheme() {
        findViewById(R.id.mainBack).setBackgroundColor(ThemesSwitcher.layoutBackColor);
        findViewById(R.id.infinity_btn).setBackground(ContextCompat.getDrawable(this,ThemesSwitcher.backColor));
    }
    private void checkButtons() {
        Cursor res = db.getUserStat();
        res.moveToFirst();
        comppletedLvlCount = res.getInt(1);
        LinearLayout ll = findViewById(R.id.linearLayout);
        for(int i = 0;i<4;i++) {
            for(int j = 0;j<3;j++) {
                Button b = (Button) ((LinearLayout) ll.getChildAt(i)).getChildAt(j);
                int lvl = Integer.parseInt(b.getText().toString());
                if(lvl-1==comppletedLvlCount | lvl==0){
                    b.setBackground(ContextCompat.getDrawable(this,R.drawable.rounded_border_light));
                }
                else if(lvl<=comppletedLvlCount) {
                    b.setBackground(getResources().getDrawable(ThemesSwitcher.backColor));
                }
            }
        }
    }
    public void goMain(View view) {
        NavUtils.navigateUpFromSameTask(this);
    }

    public void goLvl(View view) {
        boolean isAvaible = false;
        String num = ((Button)view).getText().toString();
        if(!num.equals(getString(R.string.button_map))){
            int lvl = Integer.parseInt(num);
            if(lvl-1==comppletedLvlCount)
                isAvaible = true;
            else if(lvl<=comppletedLvlCount & lvl!=0)
                isAvaible = true;
        }
        startActivity(new Intent(LevelPickActivity.this,LevelInfoActivity.class).putExtra("Number",num).putExtra("isAvaible",isAvaible));
    }

    public void goLevel(View view) {
        startActivity(new Intent(LevelPickActivity.this,LevelActivity.class).putExtra("Infinity",true));
    }
}