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
import com.example.cp.Modal.ChangePasswordModel;
import com.example.cp.Modal.ForgotPasswordModel;
import com.example.cp.Modal.SignupModel;
import com.example.cp.databinding.ActivityForgotBinding;
import com.example.cp.databinding.ActivityOtpBinding;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity {
    ActivityOtpBinding b;
    SessionManager sessionManager;
    SignupModel signupModel;
    String phone ="";
    String password ="";
    String password_confirmation ="";
    String otp ="";

    EditText action_mobile;
    TextView forgot_password;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityOtpBinding.inflate(getLayoutInflater());
        View view = b.getRoot();
        setContentView(view);
        sessionManager = new SessionManager(OtpActivity.this);
        signupModel = sessionManager.getLoginSession();
        phone=getIntent().getStringExtra("mobile");
        b.textView2.setText("sent to  "+phone);
        b.btVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()) {
                    changePassword();
                }
            }
        });




    }

    private void changePassword() {
        Log.e("Token", "Bearer " + signupModel.token);
        Log.e("djffj",""+ phone);


        Call<ChangePasswordModel> call = RetrofitClient.getInstance().getApi().changePassword("Bearer " + signupModel.token, phone,password,password_confirmation,otp);
        call.enqueue(new Callback<ChangePasswordModel>() {
            @Override
            public void onResponse(Call<ChangePasswordModel> call, Response<ChangePasswordModel> response) {
                if (response.isSuccessful()) {
                    Log.e("sushil Signup", new Gson().toJson(response.body()));
                    if (response.body().message.equalsIgnoreCase("OTP Sent to your registered mobile number.")) {
                        Toast.makeText(OtpActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(OtpActivity.this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    } else {
                        Toast.makeText(OtpActivity.this, "name or mobile or email has already been taken", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(OtpActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordModel> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }


    boolean validation() {
        password = b.etPassword.getText().toString();
        password_confirmation = b.etConfirmPassword.getText().toString();
        otp = b.etOtp.getText().toString();
        if (b.etPassword.getText().toString().isEmpty()) {
            Toast.makeText(OtpActivity.this, "password is required!!!!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password_confirmation.isEmpty()) {
            Toast.makeText(OtpActivity.this, "enter confirm password", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (otp.isEmpty()) {
            Toast.makeText(OtpActivity.this, "enter 5 digit otp", Toast.LENGTH_SHORT).show();
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
