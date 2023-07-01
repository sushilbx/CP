package com.example.cp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class WithdrawalActivity extends AppCompatActivity {


    EditText amount;

    Button withdrawbtn;

    private RadioGroup radioGroup;

    private RadioButton Payment_option1, Payment_option2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);

        amount = findViewById(R.id.amount);
        withdrawbtn = findViewById(R.id.withdrawbtn);
        radioGroup = findViewById(R.id.radioGender);
        Payment_option1 = (RadioButton) findViewById(R.id.radioButtonoption1);
        Payment_option2 = (RadioButton) findViewById(R.id.radioButtonoption2);
    }
}


