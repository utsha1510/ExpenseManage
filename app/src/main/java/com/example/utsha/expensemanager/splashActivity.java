package com.example.utsha.expensemanager;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class splashActivity extends AppCompatActivity {
private static final int SPLASH_TIMEOUT=1650;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeintent = new Intent(splashActivity.this,MainActivity.class);
                splashActivity.this.startActivity(homeintent);
                splashActivity.this.finish();
            }

        }, SPLASH_TIMEOUT );
    }
}