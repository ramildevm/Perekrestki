package com.example.perekrestki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class LevelInfoActivity extends AppCompatActivity {
private String LevelNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_info);
        LevelNum = getIntent().getStringExtra("Number");
        ((TextView)findViewById(R.id.textView)).setText(LevelNum);
    }
}