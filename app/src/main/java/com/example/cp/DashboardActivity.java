package com.example.cp;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cp.Adapter.MyPlateFormAdapter;
import com.example.cp.Adapter.PlateformAdapter;
import com.example.cp.Api.RetrofitClient;
import com.example.cp.Modal.MyPlateformModel;
import com.example.cp.Modal.PlateformModel;
import com.example.cp.Modal.PlayGame;
import com.example.cp.Modal.PlayGameModel;
import com.example.cp.Modal.SignupModel;
import com.example.cp.Modal.WalletModel;
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
    CountDownTimer timer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityDashboardBinding.inflate(getLayoutInflater());
        View view = b.getRoot();
        setContentView(view);
        sessionManager = new SessionManager(DashboardActivity.this);
        signupModel = sessionManager.getLoginSession();

        listener();

        playGame();
        myPlateForm();

        wallet();

    }

    private void listener() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);

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


    }

    private void wallet() {
        Log.e("token", "Bearer " + signupModel.token);
        /*ProgressDialog progressDialog = new ProgressDialog(DashboardActivity.this);
        progressDialog.show();
        progressDialog.setMessage("Loading...");*/
        Call<WalletModel> call = RetrofitClient.getInstance().getApi().walletBalance("Bearer " + signupModel.token);
        call.enqueue(new Callback<WalletModel>() {
            @Override
            public void onResponse(@NonNull Call<WalletModel> call, @NonNull Response<WalletModel> response) {
                if (response.isSuccessful()) {
                    //  progressDialog.dismiss();
                    Log.e("sushil Signup", new Gson().toJson(response.body()));
                    b.tvWallet.setText("₹ " + response.body().data.get(0).wallet_amount);
                } else {
                    Toast.makeText(DashboardActivity.this, "name or mobile or email has already been taken", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<WalletModel> call, Throwable t) {
                t.printStackTrace();
                //  progressDialog.dismiss();

            }
        });
    }

    private void plateform() {
        Log.e("token", "Bearer " + signupModel.token);
        /*ProgressDialog progressDialog = new ProgressDialog(DashboardActivity.this);
        progressDialog.show();
        progressDialog.setMessage("Loading...");*/
        Call<PlateformModel> call = RetrofitClient.getInstance().getApi().plateform("Bearer " + signupModel.token);
        call.enqueue(new Callback<PlateformModel>() {
            @Override
            public void onResponse(@NonNull Call<PlateformModel> call, @NonNull Response<PlateformModel> response) {
                if (response.isSuccessful()) {
                    //  progressDialog.dismiss();
                    Log.e("sushil Signup", new Gson().toJson(response.body()));
                    PlateformAdapter plateFormAdapter = new PlateformAdapter(response.body().data, DashboardActivity.this);
                    b.rvPlateform.setAdapter(plateFormAdapter);
                } else {
                    Toast.makeText(DashboardActivity.this, "name or mobile or email has already been taken", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<PlateformModel> call, Throwable t) {
                t.printStackTrace();
                //  progressDialog.dismiss();

            }
        });
    }

    //2023-07-03 13:52:00
    private void myPlateForm() {
        Log.e("token", "Bearer " + signupModel.token);
        Call<MyPlateformModel> call = RetrofitClient.getInstance().getApi().myPlateform("Bearer " + signupModel.token);
        call.enqueue(new Callback<MyPlateformModel>() {
            @Override
            public void onResponse(@NonNull Call<MyPlateformModel> call, @NonNull Response<MyPlateformModel> response) {
                if (response.isSuccessful()) {
                    Log.e("sushil Signup", new Gson().toJson(response.body()));
                    MyPlateFormAdapter myPlateFormAdapter = new MyPlateFormAdapter(response.body().data, DashboardActivity.this);
                    b.rvMyPlateform.setAdapter(myPlateFormAdapter);
                } else {
                    Toast.makeText(DashboardActivity.this, "name or mobile or email has already been taken", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<MyPlateformModel> call, Throwable t) {
                t.printStackTrace();

            }
        });

    }

    private void playGame() {
        cancelTimer();
        Log.e("token", "Bearer " + signupModel.token);
        Log.e("playGame", "Started");
        Call<PlayGameModel> call = RetrofitClient.getInstance().getApi().playGame("Bearer " + signupModel.token, "name");
        call.enqueue(new Callback<PlayGameModel>() {
            @Override
            public void onResponse(@NonNull Call<PlayGameModel> call, @NonNull Response<PlayGameModel> response) {
                Log.e("response", new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().data!=null) {
                        startGame(response.body().data);
                        plateform();
                    }
                }
            }

            @Override
            public void onFailure(Call<PlayGameModel> call, Throwable t) {
                t.printStackTrace();
                cancelTimer();
            }
        });

    }


    private void clickListener(String bet_id) {

        b.green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, GreenActivity.class);
                intent.putExtra("bet", "" + bet_id);
                intent.putExtra("select", "1");

                startActivity(intent);
            }
        });


        b.violet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, GreenActivity.class);
                intent.putExtra("bet", "" + bet_id);
                intent.putExtra("select", "10");
                startActivity(intent);
            }
        });


        b.red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, GreenActivity.class);
                intent.putExtra("bet", "" + bet_id);
                intent.putExtra("select", "2");
                startActivity(intent);
            }
        });
        b.tv0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, GreenActivity.class);
                intent.putExtra("bet", "" + bet_id);
                intent.putExtra("select", "0");
                startActivity(intent);
            }
        });
        b.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, GreenActivity.class);
                intent.putExtra("bet", "" + bet_id);
                intent.putExtra("select", "1");
                startActivity(intent);
            }
        });
        b.tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, GreenActivity.class);
                intent.putExtra("bet", "" + bet_id);
                intent.putExtra("select", "2");
                startActivity(intent);
            }
        });
        b.tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, GreenActivity.class);
                intent.putExtra("bet", "" + bet_id);
                intent.putExtra("select", "3");
                startActivity(intent);
            }
        });
        b.tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, GreenActivity.class);
                intent.putExtra("bet", "" + bet_id);
                intent.putExtra("select", "4");
                startActivity(intent);
            }
        });
        b.tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, GreenActivity.class);
                intent.putExtra("bet", "" + bet_id);
                intent.putExtra("select", "5");
                startActivity(intent);
            }
        });
        b.tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, GreenActivity.class);
                intent.putExtra("bet", "" + bet_id);
                intent.putExtra("select", "6");
                startActivity(intent);
            }
        });
        b.tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, GreenActivity.class);
                intent.putExtra("bet", "" + bet_id);
                intent.putExtra("select", "7");
                startActivity(intent);
            }
        });
        b.tv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, GreenActivity.class);
                intent.putExtra("bet", "" + bet_id);
                intent.putExtra("select", "8");
                startActivity(intent);
            }
        });
        b.tv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, GreenActivity.class);
                intent.putExtra("bet", "" + bet_id);
                intent.putExtra("select", "9");
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    void startGame(PlayGame model) {
        Log.e("startGame", new Gson().toJson(model));
        b.tvPeriod.setText("Period : " + model.bet_no);
        clickListener(model.bet_no);
        if (model.bet_end_time.getTime() > System.currentTimeMillis()) {
            timer = getCountDownTimer(model.bet_end_time.getTime() - System.currentTimeMillis());
        } else {
            timer = null;
            playGame();
        }
    }

    void cancelTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    public CountDownTimer getCountDownTimer(long milliseconds) {
        CountDownTimer countDownTimer = new CountDownTimer(milliseconds, 1000) {
            public void onTick(long duration) {
                setTime(duration);
                setNotClickable(duration < 20000);
            }

            public void onFinish() {
                cancel();
                timer=null;
                playGame();
                myPlateForm();
            }
        }.start();
        return countDownTimer;
    }

    void setTime(long duration) {
        long min = (duration / 1000) / 60;
        long sec = (duration / 1000) % 60;
        Log.e("kkkkkkkkkkkkk", "" + min + ":0" + sec);
        b.tvCount.setText(String.format(Locale.getDefault(), "%s:%02d", min, sec));
    }

    void setNotClickable(boolean notClickable) {
        if (notClickable) {
            if (b.tvStartWetting.getVisibility() == View.GONE) {
                b.tvStartWetting.setVisibility(View.VISIBLE);
            }
            b.green.setOnClickListener(null);
            b.violet.setOnClickListener(null);
            b.red.setOnClickListener(null);
            b.tv0.setOnClickListener(null);
            b.tv1.setOnClickListener(null);
            b.tv2.setOnClickListener(null);
            b.tv3.setOnClickListener(null);
            b.tv4.setOnClickListener(null);
            b.tv5.setOnClickListener(null);
            b.tv6.setOnClickListener(null);
            b.tv7.setOnClickListener(null);
            b.tv8.setOnClickListener(null);
            b.tv9.setOnClickListener(null);
        } else {
            if (b.tvStartWetting.getVisibility() == View.VISIBLE) {
                b.tvStartWetting.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
        myPlateForm();
    }
}
