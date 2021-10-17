package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Services.UserService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    JSONObject user_object = new JSONObject();
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

        try {
            user_object.put("user_email", userName.getText().toString());
            user_object.put("user_password",password.getText().toString());
            UserService userService = new UserService(MainActivity.this);
            userService.Login(new UserService.VolleyResponseListener() {
                @Override
                public void onError(String message) {
                    Toast toast = Toast.makeText(ctx, "Your login information is incorrect", Toast.LENGTH_SHORT);
                    toast.show();
                }

                @Override
                public void onResponse(JSONObject user_object) {
                    try {
                        Intent intent = new Intent(MainActivity.this, UserDashboardActivity.class);
                        intent.putExtra("username", user_object.getJSONObject("data").getString("user_email"));
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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

    public void onRegisterClick(View view) {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}