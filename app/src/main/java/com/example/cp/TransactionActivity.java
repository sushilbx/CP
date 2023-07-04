package com.example.cp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cp.Adapter.DepositeAdapter;
import com.example.cp.Adapter.WithdrawAdapter;
import com.example.cp.Api.RetrofitClient;
import com.example.cp.Modal.DepositeListModel;
import com.example.cp.Modal.SignupModel;
import com.example.cp.Modal.WalletWithdrawlModel;
import com.example.cp.databinding.ActivityAddmoneyBinding;
import com.example.cp.databinding.ActivityTransactionBinding;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionActivity extends AppCompatActivity {
    ActivityTransactionBinding b;
    SessionManager sessionManager;
    SignupModel signupModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityTransactionBinding.inflate(getLayoutInflater());
        View view = b.getRoot();
        setContentView(view);
        sessionManager = new SessionManager(TransactionActivity.this);
        signupModel = sessionManager.getLoginSession();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        deposite();
        withdrawl();


    }

    private void withdrawl() {
        Log.e("token", "Bearer " + signupModel.token);

        Call<WalletWithdrawlModel> call = RetrofitClient.getInstance().getApi().withdrawList("Bearer " + signupModel.token);
        call.enqueue(new Callback<WalletWithdrawlModel>() {
            @Override
            public void onResponse(@NonNull Call<WalletWithdrawlModel> call, @NonNull Response<WalletWithdrawlModel> response) {
                if (response.isSuccessful()) {
                    Log.e("sushil Signup", new Gson().toJson(response.body()));
                    WithdrawAdapter withdrawAdapter = new WithdrawAdapter(response.body().data, TransactionActivity.this);
                    b.rvWithdrawMoney.setAdapter(withdrawAdapter);
                } else {
                    Toast.makeText(TransactionActivity.this, "name or mobile or email has already been taken", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<WalletWithdrawlModel> call, Throwable t) {
                t.printStackTrace();


            }
        });
    }


    private void deposite() {
        Log.e("token", "Bearer " + signupModel.token);

        Call<DepositeListModel> call = RetrofitClient.getInstance().getApi().depositeList("Bearer " + signupModel.token);
        call.enqueue(new Callback<DepositeListModel>() {
            @Override
            public void onResponse(@NonNull Call<DepositeListModel> call, @NonNull Response<DepositeListModel> response) {
                if (response.isSuccessful()) {
                    Log.e("sushil Signup", new Gson().toJson(response.body()));
                    DepositeAdapter depositeAdapter = new DepositeAdapter(response.body().data, TransactionActivity.this);
                    b.rvDeposite.setAdapter(depositeAdapter);
                } else {
                    Toast.makeText(TransactionActivity.this, "name or mobile or email has already been taken", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<DepositeListModel> call, Throwable t) {
                t.printStackTrace();


            }
        });
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}



