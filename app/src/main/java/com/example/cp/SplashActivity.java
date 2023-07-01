package com.example.cp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //Start Landing Screen
                Intent mIntent = new Intent(SplashActivity.this, RegisterActivity.class);
                startActivity(mIntent);
//                    savePrefsData();
                finish();
            }
        }, 3000);

    }
}
