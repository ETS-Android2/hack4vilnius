package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.xml.validation.Validator;

public class RegisterActivity extends AppCompatActivity {
    boolean formIsValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void validateFormFields(String email, String password, String confirmedPassword) {

        if (TextUtils.isEmpty(email)) {
            Toast toast = Toast.makeText(getBaseContext(), "Email must not be empty!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        } else if (TextUtils.isEmpty(password)) {
            Toast toast = Toast.makeText(getBaseContext(), "Password must not be empty!", Toast.LENGTH_SHORT);
            toast.show();
            return;

        } else if (password.length() < 8) {
            Toast toast = Toast.makeText(getBaseContext(), "Password must be minimum 8 characters!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        } else if (!password.equals(confirmedPassword)) {
            Toast toast = Toast.makeText(getBaseContext(), "Password confirmation must match!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        } else if (TextUtils.isEmpty(confirmedPassword)) {
            Toast toast = Toast.makeText(getBaseContext(), "Confirmed password must not be empty!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        } else {
            formIsValid = true;
        }
    }

    public void onRegisterButtonClick(View view) {
        EditText email = findViewById(R.id.editTextEmail);
        EditText password = findViewById(R.id.editTextPassword);
        EditText confirmedPassword = findViewById(R.id.editTextConfirmPassword);

        validateFormFields(email.getText().toString(), password.getText().toString(), confirmedPassword.getText().toString());

        if (formIsValid) {
            Toast toast = Toast.makeText(getBaseContext(), "User registered", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
    }
}