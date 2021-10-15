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
    }

    public void goToMap(View view) {
        EditText userName = findViewById(R.id.editTextUsername);
        EditText password = findViewById(R.id.editTextPassword);

        startActivity(new Intent(UserDashboardActivity.this, MapActivity.class));
    }
}