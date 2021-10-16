package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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

    public void onSetGoalsClick(View view) {
        Intent i = new Intent(this, GoalsActivity.class);
        startActivityForResult(i, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String strEditText = data.getStringExtra("editTextValue");
                TextView goalInfo = findViewById(R.id.textViewCurrentGoal);
                goalInfo.setText(strEditText);
            }
        }
    }
}