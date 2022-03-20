package com.example.perekrestki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UserStatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_stat);

        int lvlCount = 6;

        ((TextView)findViewById(R.id.lvlcountText)).setText(""+lvlCount);

        View view = findViewById(R.id.progressBar);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = (findViewById(R.id.progressBar1).getLayoutParams().width * lvlCount)/12;
        view.setLayoutParams(layoutParams);
    }

    public void goBack(View view) { onBackPressed();
    }

    public void resetStat(View view) {
    }
}