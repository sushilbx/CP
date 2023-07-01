package com.example.cp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.cp.Adapter.InviteTablayoutAdapter;
import com.google.android.material.tabs.TabLayout;

public class InviteActivity extends AppCompatActivity {


    TextView notification,apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);

        notification = findViewById(R.id.notification);
        apply = findViewById(R.id.apply);

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InviteActivity.this,NotificationActivity.class);
                startActivity(intent);
            }
        });



        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InviteActivity.this,ApplyBalanceActivity.class);
                startActivity(intent);
            }
        });


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout.addTab(tabLayout.newTab().setText("Level 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Level 2"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final InviteTablayoutAdapter adapter = new InviteTablayoutAdapter(this, getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
