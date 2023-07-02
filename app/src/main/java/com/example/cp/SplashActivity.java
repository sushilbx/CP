package com.example.cp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private Handler mHandler;
    SessionManager sessionManager;
    Context context;
    private Runnable myRunnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = SplashActivity.this;
        context = SplashActivity.this;
        sessionManager = new SessionManager(context);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        goToNextPage();
    }

    private void goToNextPage() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sessionManager.isLoggedIn()) {

                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    finish();
                }
                else {
                    startActivity(new Intent(SplashActivity.this, SigninActivity.class));
                    finish();
                }
            }

        }, 2000);
    }

}