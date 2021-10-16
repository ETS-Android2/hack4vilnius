package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class ScoreboardActivity extends AppCompatActivity {
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        username = getIntent().getStringExtra("username");

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(),
                ScoreboardActivity.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void onHomeButtonClick(View view) {
        Intent intent = new Intent(ScoreboardActivity.this, UserDashboardActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void onCouponButtonClick (View view){
        Intent intent = new Intent(ScoreboardActivity.this, PrizeActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
    public void onMapButtonClick(View view) {
        Intent intent = new Intent(ScoreboardActivity.this, MapActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
    public void onQRCodeButtonClick(View view) {
        Intent intent = new Intent(ScoreboardActivity.this, QRCodeActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}