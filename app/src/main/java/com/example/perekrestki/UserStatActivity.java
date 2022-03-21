package com.example.perekrestki;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UserStatActivity extends AppCompatActivity {

    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_stat);
        db = new DBHelper(this);
        setData();
    }
    public void setData(){

        Cursor userData = db.getuserstat();
        int lvlCount = -1, fails = -1, hardlvl = -1;
        while (userData.moveToNext()){
            lvlCount = userData.getInt(1);
            fails = userData.getInt(2);
            hardlvl = userData.getInt(3);
        }
        Cursor lvl =  db.getlevel(hardlvl);
        lvl.moveToNext();
        ((TextView)findViewById(R.id.lvlcountText)).setText(""+lvlCount);

        View view = findViewById(R.id.progressBar);
        findViewById(R.id.progressBarBack).getLayoutParams().width = 400;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = ( 400 * lvlCount)/12;
        view.setLayoutParams(layoutParams);

        ((TextView)findViewById(R.id.failsText)).setText(""+fails);
        ((TextView)findViewById(R.id.hardlvlText)).setText(""+hardlvl);
        ((TextView)findViewById(R.id.hardlvlfailsText)).setText(""+lvl.getInt(2));

    }
    public void goBack(View view) { onBackPressed();
    }

    public void resetStat(View view) {
    }
}