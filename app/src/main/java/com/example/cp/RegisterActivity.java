package com.example.cp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cp.Utils.utils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class RegisterActivity extends AppCompatActivity {


    EditText action_name,action_mobile,action_email,action_password,action_confirmpassword,action_referal_id;

    private TextView responseTV,text1;
    TextView tvSignup;
    Button button;



    boolean bottomshow = false;
    private BottomSheetBehavior mBottomSheetBehavior;


    public static final String MY_PREFS_NAME = "MyPrefsFile";

    String url = "http://color-web.seomantras.in/api/user/register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        button = findViewById(R.id.button);
        tvSignup = findViewById(R.id.tvSignup);
        action_name = findViewById(R.id.action_name);
        action_mobile = findViewById(R.id.action_mobile);
        action_email = findViewById(R.id.action_email);
        action_password = findViewById(R.id.action_password);
        action_confirmpassword = findViewById(R.id.action_confirmpassword);
        action_referal_id = findViewById(R.id.action_referal_id);
        text1 = findViewById(R.id.text1);






        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,SigninActivity.class);
                startActivity(intent);
            }
        });



//        text1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(RegisterActivity.this,SigninActivity.class);
//                startActivity(intent);
//            }
//        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getPhoneNo = action_mobile.getText().toString();
                Pattern p = Pattern.compile(utils.regEx);
                Matcher m = p.matcher(getPhoneNo);

                String emailAddress = action_email.getText().toString().trim();
                if (!m.find()) {
                    action_mobile.setError("Invalid Phone number");
                    Toast.makeText(RegisterActivity.this, "Invalid Phone number", Toast.LENGTH_SHORT).show();
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
                    action_email.setError("invalid Email address");
                    Toast.makeText(RegisterActivity.this, "invalid Email address", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(action_name.getText().toString())) {
                    action_name.setError(getString(R.string.mandatory));
                    Toast.makeText(RegisterActivity.this, "Enter your Name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(action_mobile.getText().toString())) {
                    action_mobile.setError(getString(R.string.mandatory));
                    Toast.makeText(RegisterActivity.this, "Enter your Phone Number", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(action_password.getText().toString())) {
                    action_password.setError(getString(R.string.mandatory));
                    Toast.makeText(RegisterActivity.this, "password is mandatory", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(action_confirmpassword.getText().toString())) {
                    action_confirmpassword.setError(getString(R.string.mandatory));
                    Toast.makeText(RegisterActivity.this, "Confirm password is mandatory", Toast.LENGTH_SHORT).show();
//                } else if (TextUtils.isEmpty(motherTongue.getText().toString())) {
//                    motherTongue.setError(getString(R.string.mandatory));
//                    Toast.makeText(RegisterActivity.this, "Choose Mother Tongue", Toast.LENGTH_SHORT).show();
//                } else if (TextUtils.isEmpty(Religion.getText().toString())) {
//                    Religion.setError(getString(R.string.mandatory));
//                    Toast.makeText(RegisterActivity.this, "religion is mandatory", Toast.LENGTH_SHORT).show();
//                } else if (TextUtils.isEmpty(Subsect.getText().toString())) {
//                    Subsect.setError(getString(R.string.mandatory));
//                    Toast.makeText(RegisterActivity.this, "caste is mandatory", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(action_email.getText().toString())) {
                    action_email.setError(getString(R.string.mandatory));
                    Toast.makeText(RegisterActivity.this, "Email is mandatory", Toast.LENGTH_SHORT).show();
                } else {

                    sendDataToServer();
                }

            }
        });
    }





    private void sendDataToServer() {

        ProgressDialog pDialog = new ProgressDialog(RegisterActivity.this);
        pDialog.setTitle("Loading......");
        pDialog.setCancelable(false);
        pDialog.show();



        Map<String, String> params = new HashMap<String, String>();
        params.put("name",  action_name.getText().toString());
        params.put("email", action_email.getText().toString());
        params.put("phone", action_mobile.getText().toString());
        params.put("password", action_password.getText().toString());
        params.put("confirm_password", action_confirmpassword.getText().toString());
        params.put("refer_id", action_referal_id.getText().toString());




        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pDialog.dismiss();
//                Toast.makeText(RegisterActivity.this, "hghhh", Toast.LENGTH_SHORT).show();
                Log.e("fghfh", response.toString());

                Intent intent = new Intent(RegisterActivity.this, SigninActivity.class);
                startActivity(intent);
                try {

                    if (response.getInt("status") == 1) {
//
                        int user_id = response.getInt("id");
//                        String otp = response.getString("otp");
                        String mobile = response.getString("mobile");
//                        String gender = response.getString("gender");
////
////                        //Storing user_id using SharedPreference
                        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        editor.putString("user_id", String.valueOf(user_id));
//                        editor.putString("otp", otp);
                        editor.putString("mobile", mobile);
                        editor.putString("name", action_name.getText().toString());
                        editor.apply();


//                        Intent intent = new Intent(RegisterActivity.this, SigninActivity.class);
//                        intent.putExtra(com.example.cp.Modal.Keys.REGISTER_USER_ID, user_id);
//                        startActivity(intent);
                    } else {
                        pDialog.dismiss();
                        new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Color Prediction")
                                .setContentText("Phone number Already exist")
                                .setConfirmText("Ok")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismiss();
                                    }
                                })
                                .show();

                    }
                } catch (Exception e) {
                    pDialog.dismiss();
                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Color Prediction")
                            .setContentText("Phone number Already exist")
                            .setConfirmText("Ok")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {

                                    sDialog.dismiss();
                                }
                            })
                            .show();
                }

                Log.e("YourArrayResponse", response.toString());

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                Log.e("error is ", "" + error);
                Toast.makeText(RegisterActivity.this, "Please check your connectivity", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
        queue.add(request);

    }


    @Override
    public void onBackPressed() {
        if (bottomshow == true) {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            bottomshow = false;
        } else {
            super.onBackPressed();
        }
    }



}
