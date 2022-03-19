package com.example.perekrestki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelPickActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_pick);
    }

    public void goMain(View view) {
        NavUtils.navigateUpFromSameTask(this);
    }

    public void goLvl(View view) {
        String num = ((Button)view).getText().toString();
        startActivity(new Intent(LevelPickActivity.this,LevelInfoActivity.class).putExtra("Number",num));
    }
}