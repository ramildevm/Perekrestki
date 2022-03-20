package com.example.perekrestki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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