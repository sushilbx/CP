package com.example.cp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ApplyBalanceActivity extends AppCompatActivity {

    TextView close;

    Button search_close_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applybalance);


        close = findViewById(R.id.close);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(ApplyBalanceActivity.this, InviteActivity.class);
                startActivity(intent);

            }
        });


    }
}
