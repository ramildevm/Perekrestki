package com.example.perekrestki;

import androidx.appcompat.app.AppCompatActivity;
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
    Boolean isAvaible = false;
    private int comppletedLvlCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_pick);
        db = new DBHelper(this);
        checkButtons();
    }
    private void checkButtons() {
        Cursor res = db.getuserstat();
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
                    b.setBackground(ContextCompat.getDrawable(this,R.drawable.rounded_border_complete));
                }
            }
        }
    }
    public void goMain(View view) {
        NavUtils.navigateUpFromSameTask(this);
    }

    public void goLvl(View view) {
        String num = ((Button)view).getText().toString();
        int lvl = Integer.parseInt(num);
        if(lvl-1==comppletedLvlCount | lvl==0)
            isAvaible = true;
        else if(lvl<=comppletedLvlCount)
            isAvaible = true;
        startActivity(new Intent(LevelPickActivity.this,LevelInfoActivity.class).putExtra("Number",num).putExtra("isAvaible",isAvaible));
    }
}