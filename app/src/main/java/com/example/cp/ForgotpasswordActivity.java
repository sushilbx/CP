package com.example.cp;

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
import com.example.cp.Modal.ForgotPasswordModel;
import com.example.cp.Modal.SignupModel;
import com.example.cp.databinding.ActivityForgotBinding;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotpasswordActivity extends AppCompatActivity {
    ActivityForgotBinding b;
    SessionManager sessionManager;
    SignupModel signupModel;
    String phone ="";
    EditText action_mobile;
    TextView forgot_password;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityForgotBinding.inflate(getLayoutInflater());
        View view = b.getRoot();
        setContentView(view);
        sessionManager = new SessionManager(ForgotpasswordActivity.this);
        signupModel = sessionManager.getLoginSession();

        b.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()) {
                    forgot();
                }
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);



    }

    private void forgot() {
        Log.e("Token", "Bearer " + signupModel.token);
        Log.e("djffj",""+ phone);


        Call<ForgotPasswordModel> call = RetrofitClient.getInstance().getApi().forgotPass("Bearer " + signupModel.token, phone);
        call.enqueue(new Callback<ForgotPasswordModel>() {
            @Override
            public void onResponse(Call<ForgotPasswordModel> call, Response<ForgotPasswordModel> response) {
                if (response.isSuccessful()) {
                    Log.e("sushil Signup", new Gson().toJson(response.body()));
                    if (response.body().Message.equalsIgnoreCase("OTP Sent to your registered mobile number.")) {
                        Toast.makeText(ForgotpasswordActivity.this, response.body().Message, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ForgotpasswordActivity.this, OtpActivity.class);
                        intent.putExtra("mobile", phone);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    } else {
                        Toast.makeText(ForgotpasswordActivity.this, "name or mobile or email has already been taken", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(ForgotpasswordActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordModel> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }


    boolean validation() {
        phone = b.etMobile.getText().toString();
        if (b.etMobile.getText().toString().isEmpty()) {
            Toast.makeText(ForgotpasswordActivity.this, "phone is required!!!!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Utillllll.myMobileValid(phone)) {
            Toast.makeText(ForgotpasswordActivity.this, "phone no. should be 10 digit", Toast.LENGTH_SHORT).show();
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
