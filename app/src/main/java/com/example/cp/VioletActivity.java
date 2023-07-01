package com.example.cp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VioletActivity extends AppCompatActivity {


    int count = 0;


    Context context;

    TextView pressure,close,tv;


    Button decrement,increment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_violet);

        pressure = findViewById(R.id.pressure);
        close = findViewById(R.id.close);
        decrement = findViewById(R.id.decrement);
        increment = findViewById(R.id.increment);
        tv = findViewById(R.id.tv);

        pressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VioletActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VioletActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });



        increment.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                count ++;
                tv.setText(""+count);
            }
        });




        decrement.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                count --;
                tv.setText(""+count);
            }
        });

    }
}
