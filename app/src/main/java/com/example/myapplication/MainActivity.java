package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSignIn(View view) {
        EditText userName = findViewById(R.id.editTextUsername);
        EditText password = findViewById(R.id.editTextPassword);


        Context ctx = getApplicationContext();

        if (TextUtils.isEmpty(userName.getText().toString())) {
            Toast toast = Toast.makeText(ctx, "Please enter your username!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        } else if (TextUtils.isEmpty(password.getText().toString())) {
            Toast toast = Toast.makeText(ctx, "Please enter your password!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

//        Context ctx = getApplicationContext();

        if(TextUtils.isEmpty(userName.getText().toString())) {
            Toast toast = Toast.makeText(getBaseContext(), "Please enter your username!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        Intent intent = new Intent(MainActivity.this, UserDashboardActivity.class);
        intent.putExtra("username", userName.getText().toString());
        startActivity(intent);
    }

    public void onRegisterClick(View view) {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}