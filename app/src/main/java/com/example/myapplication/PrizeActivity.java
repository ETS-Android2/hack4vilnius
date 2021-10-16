package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PrizeActivity extends AppCompatActivity {

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prize);
        username = getIntent().getStringExtra("username");
    }
    public void onHomeButtonClick(View view) {
        Intent intent = new Intent(PrizeActivity.this, UserDashboardActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void onMapButtonClick(View view) {
        Intent intent = new Intent(PrizeActivity.this, MapActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
    public void onQRCodeButtonClick(View view) {
        Intent intent = new Intent(PrizeActivity.this, QRCodeActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
    public void onScoreButtonClick(View view) {
        Intent intent = new Intent(PrizeActivity.this, ScoreboardActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}