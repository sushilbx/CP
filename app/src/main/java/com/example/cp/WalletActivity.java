package com.example.cp;

import static com.example.cp.RegisterActivity.MY_PREFS_NAME;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class WalletActivity extends AppCompatActivity {




    CircleImageView profileImage = null;


    String profileName;
    private NavigationView navigationView;

    TextView action_addmoney,action_invite,action_wallet,action_bankaccount,tvAddMoney,action_withdrawal,action_transaction,action_changepassword,action_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);
        tvAddMoney = findViewById(R.id.tvAddMoney);

        action_addmoney = findViewById(R.id.action_addmoney);
        action_invite = findViewById(R.id.action_invite);
        action_wallet = findViewById(R.id.action_wallet);
        action_withdrawal = findViewById(R.id.action_withdrawal);
        action_transaction = findViewById(R.id.action_transaction);
        action_changepassword = findViewById(R.id.action_changepassword);
        action_bankaccount = findViewById(R.id.action_bankaccount);
        action_logout = findViewById(R.id.action_logout);





        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        profileName = prefs.getString("name", "");//"No name defined" is the default value
        String profileImage1 = prefs.getString("profile_pic", null);
        String userId = prefs.getString("user_id", null);





        tvAddMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WalletActivity.this,AddMoneyActivity.class);
                startActivity(intent);
            }
        });



        action_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WalletActivity.this,InviteActivity.class);
                startActivity(intent);
            }
        });



        action_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WalletActivity.this,WalletActivity.class);
                startActivity(intent);
            }
        });




        action_bankaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WalletActivity.this,AddBankAccountActivity.class);
                startActivity(intent);
            }
        });


        action_withdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WalletActivity.this,WithdrawalActivity.class);
                startActivity(intent);
            }
        });



        action_transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WalletActivity.this,TransactionActivity.class);
                startActivity(intent);
            }
        });



        action_changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WalletActivity.this,ForgotpasswordActivity.class);
                startActivity(intent);
            }
        });



        action_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WalletActivity.this,SigninActivity.class);
                startActivity(intent);
            }
        });


      //  profileImage = (CircleImageView) navigationView.getHeaderView(0).findViewById(R.id.iv_profileImage1);

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
