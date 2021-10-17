package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Services.UserService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.validation.Validator;

public class RegisterActivity extends AppCompatActivity {
    boolean formIsValid = false;
    JSONObject user_object = new JSONObject();

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

            try {
                user_object.put("user_email", email.getText().toString());
                user_object.put("user_password", password.getText().toString());

                UserService userService = new UserService(RegisterActivity.this);
                userService.Registration(new UserService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast toast = Toast.makeText(getBaseContext(), "User already exist", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    @Override
                    public void onResponse(JSONObject user_object) {
                        Toast toast = Toast.makeText(getBaseContext(), "User registered", Toast.LENGTH_SHORT);
                        toast.show();
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onResponse(JSONArray user_list) {

                    }

                    @Override
                    public void onResponse(int points) {

                    }
                }, user_object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}