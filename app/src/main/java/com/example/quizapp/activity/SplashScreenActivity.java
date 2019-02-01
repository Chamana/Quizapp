package com.example.quizapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import com.example.quizapp.R;
import com.example.quizapp.api.AppController;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean loggedIn=AppController.sharedPreferences.getBoolean("isLoggedIn",false);
                if(loggedIn){
                    startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));
                }else{
                    startActivity(new Intent(SplashScreenActivity.this,SignupActivity.class));
                }
            }
        },2000);
    }
}
