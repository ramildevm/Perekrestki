package com.example.perekrestki;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
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
        Cursor hardlvlres = db.getmaxlevel();
        userData.moveToFirst();
        hardlvlres.moveToFirst();
        int lvlCount = userData.getInt(1), fails = userData.getInt(2), hardlvl = hardlvlres.getInt(0);

        ((TextView)findViewById(R.id.lvlcountText)).setText(""+lvlCount);

        View view = findViewById(R.id.progressBar);
        findViewById(R.id.progressBarBack).getLayoutParams().width = 400;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = ( 400 * lvlCount)/12;
        view.setLayoutParams(layoutParams);

        ((TextView)findViewById(R.id.failsText)).setText(""+fails);
        ((TextView)findViewById(R.id.hardlvlText)).setText(""+hardlvl);
        ((TextView)findViewById(R.id.hardlvlfailsText)).setText(""+hardlvlres.getInt(1));

    }
    public void goBack(View view) { onBackPressed();
    }
    public void resetStat(View view) {
        db.updateuserstat(1,0,0,0);
        for (int i = 1;i<13;i++){
            db.updatelevel(i,0);
        }
        setData();
    }
}