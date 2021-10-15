package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onSignIn(View view) {
        EditText userName = findViewById(R.id.editTextUsername);
        EditText password = findViewById(R.id.editTextPassword);

        startActivity(new Intent(MainActivity.this, UserDashboardActivity.class));
    }

    public void onRegisterClick(View view) {
        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
    }


}