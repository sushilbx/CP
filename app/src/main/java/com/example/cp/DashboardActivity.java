package com.example.cp;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cp.Api.RetrofitClient;
import com.example.cp.Modal.PlayGameModel;
import com.example.cp.Modal.SignupModel;
import com.example.cp.databinding.ActivityDashboardBinding;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    ActivityDashboardBinding b;
    SessionManager sessionManager;
    SignupModel signupModel;
    long startTime = 0;
    long endTime = 0;
    long countDown = 0;

    String bet_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityDashboardBinding.inflate(getLayoutInflater());
        View view = b.getRoot();
        setContentView(view);
        sessionManager = new SessionManager(DashboardActivity.this);
        signupModel = sessionManager.getLoginSession();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);
        playGame();

    }

    //2023-07-03 13:52:00
    private void playGame() {
        Log.e("token", "Bearer " + signupModel.token);
        Call<PlayGameModel> call = RetrofitClient.getInstance().getApi().playGame("Bearer " + signupModel.token, "name");
        call.enqueue(new Callback<PlayGameModel>() {
            @Override
            public void onResponse(@NonNull Call<PlayGameModel> call, @NonNull Response<PlayGameModel> response) {
                if (response.isSuccessful()) {
                    Log.e("sushil Signup", new Gson().toJson(response.body()));
                    b.tvPeriod.setText("Period : " + response.body().data.bet_no);
                    bet_id = response.body().data.bet_no;

                    Calendar cal = Calendar.getInstance();
                    long msec = cal.getTimeInMillis();
                    Log.e("Milli Seconds: ", "" + msec);

                    //for response choose
                    String responseTime = response.body().data.bet_start_time;
                    Log.e("Milli Seconds2: ", "" + responseTime);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
                    long timeInMilliseconds = 0;
                    try {
                        Date mDate = sdf.parse(responseTime);
                        assert mDate != null;
                        timeInMilliseconds = mDate.getTime();
                        Log.e("Date in milli :: ", "" + timeInMilliseconds);
                        startTime = timeInMilliseconds;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Calendar cale = Calendar.getInstance();
                    long msecc = cale.getTimeInMillis();
                    Log.e("Milli Seconds: ", "" + msecc);

                    //for response choose
                    String responseTimee = response.body().data.bet_end_time;
                    Log.e("Milli Seconds2: ", "" + responseTimee);
                    SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
                    long timeInMillisecondss = 0;
                    try {
                        Date mDate = sdff.parse(responseTimee);
                        assert mDate != null;
                        timeInMillisecondss = mDate.getTime();
                        Log.e("Date in milli :: ", "" + timeInMilliseconds);
                        endTime = timeInMillisecondss;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    countDown = endTime - startTime;
                    Log.e("count", "" + countDown);
                    new CountDownTimer(countDown, 1000) {

                        public void onTick(long duration) {

                            long Mmin = (duration / 1000) / 60;
                            long Ssec = (duration / 1000) % 60;
                            if (Ssec < 10) {
                                b.tvCount.setText("" + Mmin + ":0" + Ssec);
                            } else b.tvCount.setText("" + Mmin + ":" + Ssec);
                        }

                        public void onFinish() {
                            playGame();
                        }
                    }.start();
                    clickListener();
                } else {
                    Toast.makeText(DashboardActivity.this, "name or mobile or email has already been taken", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<PlayGameModel> call, Throwable t) {
                t.printStackTrace();

            }
        });

    }


    private void clickListener() {
        b.addmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, AddMoneyActivity.class);
                startActivity(intent);
            }
        });


        b.readrules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, ReadrulesActivity.class);
                startActivity(intent);
            }
        });


        b.green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, GreenActivity.class);
                intent.putExtra("bet", ""+bet_id);
                intent.putExtra("select", "1");

                startActivity(intent);
            }
        });


        b.violet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, VioletActivity.class);
                startActivity(intent);
            }
        });


        b.red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, RedActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
