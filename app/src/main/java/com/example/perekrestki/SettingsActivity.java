package com.example.perekrestki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        RadioButton rY = findViewById(R.id.radioButtonY);
        rY.setOnClickListener(radioButtonClickListener);
        RadioButton rG = findViewById(R.id.radioButtonG);
        rG.setOnClickListener(radioButtonClickListener);
        if(ThemesSwitcher.textColor== Color.parseColor("#FDB912"))
            rY.setChecked(true);
        else
            rG.setChecked(true);
    }
    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioButton r = (RadioButton) v;
            switch (r.getId()){
                case R.id.radioButtonY:
                    ThemesSwitcher.switchText("#FDB912");
                    ThemesSwitcher.switchBack(ContextCompat.getDrawable(SettingsActivity.this,R.drawable.rounded_border_complete_y));
                    break;
                case R.id.radioButtonG:
                    ThemesSwitcher.switchText("#00FF38");
                    ThemesSwitcher.switchBack(ContextCompat.getDrawable(SettingsActivity.this,R.drawable.rounded_border_complete_g));
            break;
            }
        }
    };
    public void goStatPage(View view) {
        startActivity(new Intent(SettingsActivity.this,UserStatActivity.class));
    }

    public void goMain(View view) {
        startActivity(new Intent(SettingsActivity.this,MainActivity.class));
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}