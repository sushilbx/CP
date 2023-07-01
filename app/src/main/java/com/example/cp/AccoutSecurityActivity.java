package com.example.cp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AccoutSecurityActivity extends AppCompatActivity {
    EditText oldpassword,newpassword,confirmpassword;


    Button upd_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountsecurity);

        oldpassword = findViewById(R.id.oldpassword);
        newpassword = findViewById(R.id.newpassword);
        confirmpassword = findViewById(R.id.confirmpassword);
        upd_btn = findViewById(R.id.upd_btn);

    }
}

