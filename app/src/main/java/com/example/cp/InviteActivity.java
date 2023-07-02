package com.example.cp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.cp.Adapter.InviteTablayoutAdapter;
import com.example.cp.Api.RetrofitClient;
import com.example.cp.Modal.InviteModel;
import com.example.cp.Modal.SignupModel;
import com.example.cp.Modal.WalletDepositeModel;
import com.example.cp.databinding.ActivityInviteBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InviteActivity extends AppCompatActivity {

    ActivityInviteBinding b;
    SessionManager sessionManager;
    SignupModel signupModel;
    String name = "";
    String phone = "";
    String email = "";
    String refered_by = "";
    TextView notification,apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityInviteBinding.inflate(getLayoutInflater());
        View view = b.getRoot();
        setContentView(view);
        sessionManager = new SessionManager(InviteActivity.this);
        signupModel = sessionManager.getLoginSession();



        b.btInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()) {
                    signUp();
                }
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);

        notification = findViewById(R.id.notification);
        apply = findViewById(R.id.apply);

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InviteActivity.this,NotificationActivity.class);
                startActivity(intent);
            }
        });



        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InviteActivity.this,ApplyBalanceActivity.class);
                startActivity(intent);
            }
        });


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout.addTab(tabLayout.newTab().setText("Level 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Level 2"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final InviteTablayoutAdapter adapter = new InviteTablayoutAdapter(this, getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }




    private void signUp() {
        Log.e("Token", "Bearer " + signupModel.token);
        Log.e("djffj",""+ name);
        Log.e("djffj",""+ phone);

        Call<InviteModel> call = RetrofitClient.getInstance().getApi().inviteUser("Bearer " + signupModel.token, name, phone,email,refered_by);
        call.enqueue(new Callback<InviteModel>() {
            @Override
            public void onResponse(Call<InviteModel> call, Response<InviteModel> response) {
                if (response.isSuccessful()) {
                    Log.e("sushil Signup", new Gson().toJson(response.body()));

                    if (response.body().message.equalsIgnoreCase("invite Send Successfully")) {
                        Toast.makeText(InviteActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(InviteActivity.this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    } else {
                        Toast.makeText(InviteActivity.this, "name or mobile or email has already been taken", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(InviteActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InviteModel> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }


    boolean validation() {
        name = b.etName.getText().toString();
        phone = b.etPhone.getText().toString();
        email = b.etEmail.getText().toString();
        refered_by = b.etRefer.getText().toString();



        if (b.etName.getText().toString().isEmpty()) {
            Toast.makeText(InviteActivity.this, "name is required!!!!", Toast.LENGTH_SHORT).show();
            return false;

        } else if (b.etPhone.getText().toString().isEmpty()) {
            Toast.makeText(InviteActivity.this, "mobile no. is required!!!!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (b.etEmail.getText().toString().isEmpty()) {
            Toast.makeText(InviteActivity.this, "email is required!!!!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (b.etRefer.getText().toString().isEmpty()) {
            Toast.makeText(InviteActivity.this, "Ref Id is required!!!!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
