package com.example.cp;

import static com.example.cp.RegisterActivity.MY_PREFS_NAME;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomerSupportActivity extends AppCompatActivity {


    TextView action_invite,action_wallet,action_changepassword,action_bankaccount,action_customerSupport,textView_name,textView_id,textView_mobile,textView_availablebalance,action_addmoney,notification;

    ImageView whatsapp, telegram;


    CircleImageView profileImage = null;


    String profileName;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customersupport);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);

        action_invite = findViewById(R.id.action_invite);
        action_wallet = findViewById(R.id.action_wallet);
        action_changepassword = findViewById(R.id.action_changepassword);
        action_bankaccount = findViewById(R.id.action_bankaccount);
        action_customerSupport = findViewById(R.id.action_customerSupport);
        whatsapp = findViewById(R.id.whatsapp);
        telegram = findViewById(R.id.telegram);
        textView_name = findViewById(R.id.textView_name);
        textView_id = findViewById(R.id.textView_id);
        textView_mobile = findViewById(R.id.textView_mobile);
        textView_availablebalance = findViewById(R.id.textView_availablebalance);
        action_addmoney = findViewById(R.id.action_addmoney);
        notification = findViewById(R.id.notification);



        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        profileName = prefs.getString("name", "");//"No name defined" is the default value
        String profileImage1 = prefs.getString("profile_pic", null);
        String userId = prefs.getString("user_id", null);





        action_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerSupportActivity.this,InviteActivity.class);
                startActivity(intent);
            }
        });



        action_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerSupportActivity.this,WalletActivity.class);
                startActivity(intent);
            }
        });




        action_bankaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerSupportActivity.this,AddBankAccountActivity.class);
                startActivity(intent);
            }
        });






        action_addmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerSupportActivity.this,AddMoneyActivity.class);
                startActivity(intent);
            }
        });






        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerSupportActivity.this,NotificationActivity.class);
                startActivity(intent);
            }
        });






//        action_withdrawal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(WalletActivity.this,WithdrawalActivity.class);
//                startActivity(intent);
//            }
//        });



//        action_transaction.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(WalletActivity.this,TransactionActivity.class);
//                startActivity(intent);
//            }
//        });



        action_changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerSupportActivity.this,ForgotpasswordActivity.class);
                startActivity(intent);
            }
        });


        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendwts();
            }
        });


        telegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendtele();
            }
        });



     //   profileImage = (CircleImageView) navigationView.getHeaderView(0).findViewById(R.id.iv_profileImage1);

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
    protected void sendwts(){
        String smsNumber = "2126123456789"; // E164 format without '+' sign
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        //  Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT, "test \n");
        sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
        sendIntent.setPackage("com.whatsapp");

        startActivity(sendIntent);
    }


    protected void sendtele(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        int YourTextHere = 0;
        sendIntent.putExtra(Intent.EXTRA_TEXT,YourTextHere);
        sendIntent.setType("text/plain");
        sendIntent.setPackage("org.telegram.messenger");
        try{
            startActivity(sendIntent);
        }
        catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(getApplicationContext(),"Install Telegram",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}

