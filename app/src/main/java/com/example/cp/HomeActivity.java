package com.example.cp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import com.example.cp.Modal.UserSessionManager;
import com.example.cp.Utils.LoginKeys;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout mDrawerLayout;

    private NavigationView navigationView;


    Toolbar toolbar;


    AlertDialog.Builder builder;

    private MenuItem item;

    TextView action_home, action_win, action_Profile;

    private ActionBarDrawerToggle mDrawerToggle;

    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        action_home = findViewById(R.id.action_home);
        action_win = findViewById(R.id.action_win);
        action_Profile = findViewById(R.id.action_Profile);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        builder = new AlertDialog.Builder(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_menu_24);
        setTitle("");




        mDrawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView_dashBoard);



        navigationView.setNavigationItemSelectedListener(this);

        action_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
        action_win.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(HomeActivity.this, DashboardActivity.class);
                startActivity(intent);

            }
        });

        action_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);


            }
        });




    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {

            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        mDrawerLayout.closeDrawers();

        if (item.getItemId() == R.id.invite) {

//            case R.id.invite:
            Intent intent = new Intent(HomeActivity.this, InviteActivity.class);
            startActivity(intent);


        }


        else if (item.getItemId() == R.id.home) {
                   /* bottomNavigation.getMenu ().findItem (R.id.action_myMatches).setChecked (true);
                    getSupportFragmentManager ().beginTransaction ().replace (R.id.frame_layout, new matchesFragment()).commit ();
                    break;*/
            startActivity(new Intent(HomeActivity.this, InviteActivity.class));
        }

        else if (item.getItemId() == R.id.wallet) {
                startActivity(new Intent(HomeActivity.this, WalletActivity.class));

                }
        else if (item.getItemId() == R.id.resetpassword) {
            startActivity(new Intent(HomeActivity.this, ForgotpasswordActivity.class));
            finish();

        }

        else if (item.getItemId() == R.id.customerSupport) {
                Intent i = new Intent(HomeActivity.this, CustomerSupportActivity.class);
                startActivity(i);



        }


        else if (item.getItemId() == R.id.action_tandcDesclaimer) {
            Intent i = new Intent(HomeActivity.this, SigninActivity.class);
            startActivity(i);



        }

        return super.onOptionsItemSelected(item);

    }






}



