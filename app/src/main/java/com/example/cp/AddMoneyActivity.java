package com.example.cp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cp.Adapter.DepositeAdapter;
import com.example.cp.Adapter.PlateformAdapter;
import com.example.cp.Api.RetrofitClient;
import com.example.cp.Modal.AddBankAccountModel;
import com.example.cp.Modal.DepositeListModel;
import com.example.cp.Modal.PlateformModel;
import com.example.cp.Modal.SignupModel;
import com.example.cp.Modal.WalletDepositeModel;
import com.example.cp.Modal.WalletModel;
import com.example.cp.databinding.ActivityAddmoneyBinding;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMoneyActivity extends AppCompatActivity {
    ActivityAddmoneyBinding b;
    SessionManager sessionManager;
    SignupModel signupModel;
    String amount = "";
    String tn_ref_no = "";

    Button money_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityAddmoneyBinding.inflate(getLayoutInflater());
        View view = b.getRoot();
        setContentView(view);
        sessionManager = new SessionManager(AddMoneyActivity.this);
        signupModel = sessionManager.getLoginSession();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);

        money_btn = findViewById(R.id.money_btn);
        b.moneyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()) {
                    addMoney();
                }
            }
        });
        wallet();
        deposite();

    }

    private void addMoney() {
        Log.e("Token", "Bearer " + signupModel.token);
        Log.e("djffj",""+ amount);
        Log.e("djffj",""+ tn_ref_no);

        Call<WalletDepositeModel> call = RetrofitClient.getInstance().getApi().walletDeposite("Bearer " + signupModel.token, amount, tn_ref_no);
        call.enqueue(new Callback<WalletDepositeModel>() {
            @Override
            public void onResponse(Call<WalletDepositeModel> call, Response<WalletDepositeModel> response) {
                if (response.isSuccessful()) {
                    Log.e("sushil Signup", new Gson().toJson(response.body()));
                    if (response.body().message.equalsIgnoreCase("Request Submitted Successfully")) {
                        Toast.makeText(AddMoneyActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddMoneyActivity.this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    } else {
                        Toast.makeText(AddMoneyActivity.this, "name or mobile or email has already been taken", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(AddMoneyActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WalletDepositeModel> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }


    boolean validation() {
        amount = b.amount.getText().toString();
        tn_ref_no = b.etRef.getText().toString();



        if (b.amount.getText().toString().isEmpty()) {
            Toast.makeText(AddMoneyActivity.this, "amount is required!!!!", Toast.LENGTH_SHORT).show();
            return false;

        } else if (b.etRef.getText().toString().isEmpty()) {
            Toast.makeText(AddMoneyActivity.this, "Transaction Ref Id is required!!!!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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
                    b.tvWallet.setText("₹ "+response.body().data.get(0).wallet_amount);
                } else {
                    Toast.makeText(AddMoneyActivity.this, "name or mobile or email has already been taken", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<WalletModel> call, Throwable t) {
                t.printStackTrace();
                //  progressDialog.dismiss();

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
                    DepositeAdapter depositeAdapter = new DepositeAdapter(response.body().data, AddMoneyActivity.this);
                    b.rvDeposite.setAdapter(depositeAdapter);
                } else {
                    Toast.makeText(AddMoneyActivity.this, "name or mobile or email has already been taken", Toast.LENGTH_SHORT).show();

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

