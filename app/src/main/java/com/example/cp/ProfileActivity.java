package com.example.cp;

import static com.example.cp.RegisterActivity.MY_PREFS_NAME;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {


    TextView notification;

    CircleImageView profileImage = null;


    String profileName;
    private NavigationView navigationView;

    TextView action_invite,action_wallet,action_account,action_bank,action_customerSupport,action_addmoney,textView_name,textView_id,textView_mobile,textView_availablebalance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



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

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        profileName = prefs.getString("name", "");//"No name defined" is the default value
        String profileImage1 = prefs.getString("profile_pic", null);
        String userId = prefs.getString("user_id", null);




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





        profileImage = (CircleImageView) navigationView.getHeaderView(0).findViewById(R.id.iv_profileImage1);

        try {
            if (profileImage1.equals("Male") & profileImage1.isEmpty()) {
                profileImage.setImageResource(R.drawable.male);
            } else if (profileImage1.equals("Female")) {
                profileImage.setImageResource(R.drawable.female);
            } else {
              //  Picasso.with(getApplicationContext()).load(profileImage1).fit().into(profileImage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }




    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
