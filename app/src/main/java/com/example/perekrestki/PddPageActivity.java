package com.example.perekrestki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class PddPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdd_page);
        findViewById(R.id.mainBack).setBackgroundColor(ThemesSwitcher.layoutBackColor);
        ((WebView)findViewById(R.id.webView)).loadUrl("file:///android_asset/pdd.html");
    }

    public void goBack(View view) {
        onBackPressed();
    }
}