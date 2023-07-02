package com.example.cp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cp.Api.RetrofitClient;
import com.example.cp.Modal.InviteModel;
import com.example.cp.Modal.SignupModel;
import com.example.cp.Modal.WalletWithdrawlModel;
import com.example.cp.databinding.ActivityInviteBinding;
import com.example.cp.databinding.ActivityWithdrawalBinding;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WithdrawalActivity extends AppCompatActivity {
    ActivityWithdrawalBinding b;
    SessionManager sessionManager;
    SignupModel signupModel;
    String amount = "";
    String fee = "";
    String transfer_bank = "";
    String transfer_name = "";
    String transfer_type = "";
    String transfer_account = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityWithdrawalBinding.inflate(getLayoutInflater());
        View view = b.getRoot();
        setContentView(view);
        sessionManager = new SessionManager(WithdrawalActivity.this);
        signupModel = sessionManager.getLoginSession();

        b.tvAddMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WithdrawalActivity.this, AddMoneyActivity.class);
                startActivity(intent);
            }
        });

        b.tvWithdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()) {
                    withdrawal();
                }
            }
        });

    }

    private void withdrawal() {
        Log.e("Token", "Bearer " + signupModel.token);
        Log.e("djffj", "" + amount);
        Log.e("djffj", "" + fee);

        Call<WalletWithdrawlModel> call = RetrofitClient.getInstance().getApi().walletWithdrawl("Bearer " + signupModel.token, amount, fee, transfer_bank, transfer_name, transfer_type, transfer_account);
        call.enqueue(new Callback<WalletWithdrawlModel>() {
            @Override
            public void onResponse(Call<WalletWithdrawlModel> call, Response<WalletWithdrawlModel> response) {
                if (response.isSuccessful()) {
                    Log.e("sushil Signup", new Gson().toJson(response.body()));

                    if (response.body().message.equalsIgnoreCase("Request Submitted Successfully")) {
                        Toast.makeText(WithdrawalActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(WithdrawalActivity.this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    } else {
                        Toast.makeText(WithdrawalActivity.this, "name or mobile or email has already been taken", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(WithdrawalActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WalletWithdrawlModel> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }


    boolean validation() {
        amount = b.amount.getText().toString();
        fee = b.etFee.getText().toString();
        transfer_bank = b.etTransfer.getText().toString();
        transfer_name = b.etAccountHolderName.getText().toString();
        transfer_type = b.etTransType.getText().toString();
        transfer_account = b.etAcNumber.getText().toString();


        if (b.amount.getText().toString().isEmpty()) {
            Toast.makeText(WithdrawalActivity.this, "amount is required!!!!", Toast.LENGTH_SHORT).show();
            return false;

        } else if (b.etFee.getText().toString().isEmpty()) {
            Toast.makeText(WithdrawalActivity.this, "fee is required!!!!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (b.etTransfer.getText().toString().isEmpty()) {
            Toast.makeText(WithdrawalActivity.this, "Bank account name is required!!!!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (b.etAccountHolderName.getText().toString().isEmpty()) {
            Toast.makeText(WithdrawalActivity.this, "Account holder name is required!!!!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (b.etTransType.getText().toString().isEmpty()) {
            Toast.makeText(WithdrawalActivity.this, "Transfer type is required!!!!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (b.etAcNumber.getText().toString().isEmpty()) {
            Toast.makeText(WithdrawalActivity.this, "Account no. is required!!!!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}


