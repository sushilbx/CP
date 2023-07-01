package com.example.cp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GreenActivity extends AppCompatActivity {



    int count = 0;
    Context context;

    TextView pressure, close,tv;

    Button decrement,increment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green);


        pressure = findViewById(R.id.pressure);
        close = findViewById(R.id.close);
        decrement = findViewById(R.id.decrement);
        increment = findViewById(R.id.increment);
        tv = findViewById(R.id.tv);




        context=this;

        pressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GreenActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GreenActivity.this, DashboardActivity.class);
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


//    /**
//     * This method is called when the order button is clicked.
//     */
//    public void submitOrder(View view) {
//        int price = quantity*5;
////        displayPrice(price);
//    }
//
//    public void increment (View view) {
//        quantity = quantity + 1;
//        display(quantity);
//    }
//
//    public void decrement (View view) {
//        if (quantity>0){
//            quantity = quantity - 1;
//            display(quantity);
//        }
//    }
//
//    /**
//     * This method displays the given quantity value on the screen.
//     */
//    private void display(int number) {
////        EditText quantityText = (EditText) findViewById(R.id.quantity_textview);
//        decrement.setText("" + number);
//    }
//
//    /**
//     * This method displays the given price on the screen.
//     */
////    private void displayPrice(int number) {
//////        TextView priceTextView = (TextView) findViewById(R.id.price_textview);
//////        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
////        increment.setText("" - number);
////
////    }


}
