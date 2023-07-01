package com.example.cp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationActivity extends AppCompatActivity {


    Button search_close_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog_layout);


        search_close_btn = findViewById(R.id.close_btn);


        search_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(NotificationActivity.this, InviteActivity.class);
                startActivity(intent);

            }
        });


    }
}
