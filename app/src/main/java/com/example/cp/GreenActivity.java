package com.example.cp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cp.Api.RetrofitClient;
import com.example.cp.Modal.SignupModel;
import com.example.cp.Modal.WalletDepositeModel;
import com.example.cp.databinding.ActivityAddmoneyBinding;
import com.example.cp.databinding.ActivityGreenBinding;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GreenActivity extends AppCompatActivity {
    ActivityGreenBinding b;
    SessionManager sessionManager;
    SignupModel signupModel;
    String bet_id = "";
    String select = "";
    String contract_money = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityGreenBinding.inflate(getLayoutInflater());
        View view = b.getRoot();
        setContentView(view);
        sessionManager = new SessionManager(GreenActivity.this);
        signupModel = sessionManager.getLoginSession();

        bet_id= getIntent().getStringExtra("bet");
        select= getIntent().getStringExtra("select");

        b.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()) {
                    addMoney();
                }
            }
        });

    }

    private void addMoney() {
        Log.e("Token", "Bearer " + signupModel.token);
        Log.e("bet_id",""+ bet_id);
        Log.e("select",""+ select);
        Log.e("contract_money",""+ contract_money);

        Call<WalletDepositeModel> call = RetrofitClient.getInstance().getApi().betStart("Bearer " + signupModel.token, bet_id, select,contract_money);
        call.enqueue(new Callback<WalletDepositeModel>() {
            @Override
            public void onResponse(Call<WalletDepositeModel> call, Response<WalletDepositeModel> response) {
                if (response.isSuccessful()) {
                    Log.e("sushil Signup", new Gson().toJson(response.body()));
                    if (response.body().message.equalsIgnoreCase("Bet Submitted Successfully")) {
                        Toast.makeText(GreenActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(GreenActivity.this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    } else {
                        Toast.makeText(GreenActivity.this, "name or mobile or email has already been taken", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(GreenActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WalletDepositeModel> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }


    boolean validation() {
        contract_money = b.etContract.getText().toString();

        if (b.etContract.getText().toString().isEmpty()) {
            Toast.makeText(GreenActivity.this, "amount is required!!!!", Toast.LENGTH_SHORT).show();
            return false;

        }
        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}


