package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onRegister(View view) {
        EditText username = findViewById(R.id.editTextRegisterUsername);
        EditText email = findViewById(R.id.editTextRegisterEmail);
        EditText password = findViewById(R.id.editTextRegisterPassword);

        TextView registerFormInfo = findViewById(R.id.textRegisterFormInfo);
        registerFormInfo.setText(username.getText().toString() + " " + email.getText().toString() + " " + password.getText().toString());
    }
}