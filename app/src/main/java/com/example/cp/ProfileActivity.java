package com.example.cp;
import static com.example.cp.RegisterActivity.MY_PREFS_NAME;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.cp.Api.RetrofitClient;
import com.example.cp.Modal.ProfileModel;
import com.example.cp.Modal.SignupModel;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ProfileActivity extends AppCompatActivity {

    SessionManager sessionManager;
    SignupModel signupModel;
    String url = "http://color-web.seomantras.in/api/user/profile";
    TextView notification;


    CircleImageView profileImage = null;

    int position;
    String id;

    String profileName;
    private NavigationView navigationView;

    TextView action_invite,action_wallet,action_account,action_bank,action_customerSupport,action_addmoney,textView_name,textView_id,textView_mobile,textView_availablebalance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sessionManager = new SessionManager(ProfileActivity.this);
        signupModel = sessionManager.getLoginSession();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);



        action_invite = findViewById(R.id.action_invite);
        action_wallet = findViewById(R.id.action_wallet);
        action_account = findViewById(R.id.action_account);
        action_wallet = findViewById(R.id.action_wallet);
        action_bank = findViewById(R.id.action_bank);
        action_customerSupport = findViewById(R.id.action_customerSupport);
        action_addmoney = findViewById(R.id.action_addmoney);
        notification = findViewById(R.id.notification);
        textView_name = findViewById(R.id.textView_name);
        textView_id = findViewById(R.id.textView_id);
        textView_mobile = findViewById(R.id.textView_mobile);
        textView_availablebalance = findViewById(R.id.textView_availablebalance);



//        Intent i = getIntent();
//        // Get the listview item click position
//        position = i.getExtras().getInt("position");
//        id = i.getStringExtra("id");
////
        SharedPreferences sh = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);



        getDetailsFromServer();


        action_addmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,AddMoneyActivity.class);
                startActivity(intent);
            }
        });


        action_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,InviteActivity.class);
                startActivity(intent);
            }
        });



        action_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,WalletActivity.class);
                startActivity(intent);
            }
        });




        action_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,ForgotpasswordActivity.class);
                startActivity(intent);
            }
        });



        action_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,AddBankAccountActivity.class);
                startActivity(intent);
            }
        });




        action_customerSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,CustomerSupportActivity.class);
                startActivity(intent);
            }
        });


        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,ProfileNotinActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getDetailsFromServer() {
        Log.e("token", "Bearer " + signupModel.token);
        Call<ProfileModel> call = RetrofitClient.getInstance().getApi().profile("Bearer " + signupModel.token);
        call.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(@NonNull Call<ProfileModel> call, @NonNull Response<ProfileModel> response) {
                if (response.isSuccessful()) {
                    Log.e("sushil Signup", new Gson().toJson(response.body()));
                    textView_name.setText(":   " + response.body().data.get(0).name);
//                                gender.setText(":   " + tm_gender);
                    textView_id.setText(":   " + response.body().data.get(0).id);
                    textView_mobile.setText(":   " + response.body().data.get(0).phone);
                    textView_availablebalance.setText(":   " + response.body().data.get(0).wallet_amount);
                } else {
                    Toast.makeText(ProfileActivity.this, "name or mobile or email has already been taken", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }


}
