package com.example.cp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cp.Modal.SignupModel;
import com.example.cp.Modal.UserSessionManager;
import com.example.cp.Utils.LoginKeys;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class SigninActivity extends AppCompatActivity {


    UserSessionManager session;
    TextView tvSignup,forgot_password,action_mobile,action_password;
    Button button;
    SessionManager sessionManager;
    SignupModel signupModel;

    String url = "http://color-web.seomantras.in/api/user/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        sessionManager = new SessionManager(SigninActivity.this);
        signupModel = sessionManager.getLoginSession();

        button = findViewById(R.id.button);
        tvSignup = findViewById(R.id.tvSignup);
        forgot_password = findViewById(R.id.forgot_password);
        action_mobile = findViewById(R.id.action_mobile);
        action_password = findViewById(R.id.action_password);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(SigninActivity.this,HomeActivity.class);
//                startActivity(intent);
                login();
            }
        });


        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SigninActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });



        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SigninActivity.this,ForgotpasswordActivity.class);
                startActivity(intent);
            }
        });


    }


//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
//            case R.id.button: // called when login button is pressed
                if (action_mobile.getText().toString().trim().equals("")) {  //validating if phno is empty
                    Toast.makeText(getApplicationContext(), "Mobile Number cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(action_password.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Password cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    login(); // if both fields arew not empty call login
                }
//                break;
            if (v.getId() == R.id.forgot_password) {
//                case R.id.forgot_password:  // called when forgot pwd button is pressed
                    startActivity(new Intent(SigninActivity.this, ForgotpasswordActivity.class));
                    finish();
                    overridePendingTransition(R.anim.right_enter, R.anim.left_out);
//                    break;
//            case R.id.createAccount:  // registeration process starts
//
//                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
//
//                break;
            }
        }

    }


    private void login() {
        ProgressDialog pDialog = new ProgressDialog(SigninActivity.this);
        pDialog.setTitle("Loading......");
        pDialog.setCancelable(false);
        pDialog.show();

        Map<String, String> params = new HashMap<String, String>(); //adding phno and pwd to hashmap object
        params.put("phone", action_mobile.getText().toString());
        params.put("password", action_password.getText().toString());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {  // called when successvalidation
                pDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                Log.e("fghfh", response.toString());

                Gson gson = new Gson();
                SignupModel model = gson.fromJson(String.valueOf(response), SignupModel.class);
                sessionManager.createLoginSession(model);
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);//  after adding session home activity is called
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // add new flag to create new activity
                intent.putExtra(LoginKeys.calledFromSigninActivity, "MainActivity");  //because home activity is called from login activity identificastion and didnt called from splash activity
                finish();
                startActivity(intent);


                try {
                    // parsing json object to string
                    String success = response.getString("status");
                    String name = response.getString("name");
                    String email = response.getString("email");
                    String phone = response.getString("phone");
                    String gender = response.getString("gender");
                    String profile_pic = response.getString("profile_pic");



                    if (success.equals("1")) {
//                        if(autoLogin.isChecked()){ // when auto login check box is checked
//                            if(profile_pic.equals("") || profile_pic.isEmpty()){  // if profile pic is not there
//                                nder.equals("Male")){
//                                    profile_pic = "Male"; // if gender is male
//                                }else{
//                                    profile_pic = "Female"; // if gender is female
//                                }
//                            }
//                            session.createUserLoginSession(name,email,phone,getApplicationContext(),true/*this parametr is when auto login is checked passing true*/);// json array response is stored in UserSessionManager
//                        }else{  // when auto login is not checked
                            if(profile_pic.equals("") || profile_pic.isEmpty()){
                                if(gender.equals("Male")){
                                    profile_pic = "Male";
                                }else{
                                    profile_pic = "Female";
                                }
                            }
//                            session.createUserLoginSession(gender,user_id,name,profile_pic,getApplicationContext(),false/*this parametr is when auto login is not checked passing true*/);// json array response is stored in UserSessionManager
//                        }

                        session.createUserLoginSession(gender,phone, email, name,profile_pic, getApplicationContext(), true/*this parametr is when auto login is checked passing true*/);// json array response is stored in UserSessionManager
//                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);//  after adding session home activity is called
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // add new flag to create new activity
//                        intent.putExtra(LoginKeys.calledFromSigninActivity, "MainActivity");  //because home activity is called from login activity identificastion and didnt called from splash activity
//                        finish();
//                        startActivity(intent);

//                        overridePendingTransition(R.anim.right_enter, R.anim.left_out);  // animation for start activity


                    } else {
                        Toast.makeText(getApplicationContext(), "Something is wrong", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    // when username or pwd is incorreect
                    new SweetAlertDialog(SigninActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Color Prediction")
                            .setContentText("Invalid Credentials")
                            .setConfirmText("Ok")
                            .show();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();

            }
        });
        RequestQueue queue = Volley.newRequestQueue(SigninActivity.this); // hitting the api to network
        queue.add(request);

    }


}
