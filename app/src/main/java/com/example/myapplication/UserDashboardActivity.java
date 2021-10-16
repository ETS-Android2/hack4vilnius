package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UserDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        String username = getIntent().getStringExtra("username");
        TextView loginInfo = findViewById(R.id.welconeText);
        loginInfo.setText("Sveiki " + username + "!");
    }

    public void goToMap(View view) {
        startActivity(new Intent(UserDashboardActivity.this, MapActivity.class));
    }
}