package com.example.perekrestki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ((TextView)findViewById(R.id.textViewLvl)).setText(getIntent().getStringExtra("text"));
        ((ImageView)findViewById(R.id.map_img)).setImageResource(getIntent().getIntExtra("Number",R.drawable.level_map));
    }

    public void goBack(View view) {
        onBackPressed();
    }
}