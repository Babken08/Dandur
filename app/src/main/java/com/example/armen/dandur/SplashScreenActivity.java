package com.example.armen.dandur;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.armen.dandur.activity.HomeActivity;
import com.example.armen.dandur.util.DandurConstants;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int with  = displayMetrics.widthPixels;
        DandurConstants.Companion.setDeviceWith(with);
        SharedPreferences preferences = getSharedPreferences("DARK_BACKROUND", Context.MODE_PRIVATE);
        boolean isDarkBackround  = preferences.getBoolean("dark_background", false);
        DandurConstants.Companion.setDarkBackground(isDarkBackround);
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
