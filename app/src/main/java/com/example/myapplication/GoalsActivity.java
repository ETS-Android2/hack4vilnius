package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class GoalsActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> adapter;
    String[] goals = {"Movie ticket", "A cup of coffee", "Pen"};
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        username = getIntent().getStringExtra("username");

        listView = (ListView) findViewById(R.id.listViewGoalsList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, goals);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int points = 0;
                String selectedItem = listView.getItemAtPosition(position).toString();

                switch (selectedItem) {
                    case "Movie ticket":
                        points = 5000;
                        break;

                    case "A cup of coffee":
                        points = 2000;
                        break;
                    case "Pen":
                        points = 500;
                        break;
                    default:
                        points = 100;
                }

                Intent intent = new Intent();
                intent.putExtra("currentGoal", "Your current goal: " + selectedItem);
                intent.putExtra("goalPoints", Integer.toString(points));
                Toast toast = Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " selected", Toast.LENGTH_LONG);
                toast.show();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    public void onHomeButtonClick(View view) {
        Intent intent = new Intent(GoalsActivity.this, UserDashboardActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void onCouponButtonClick(View view) {
        Intent intent = new Intent(GoalsActivity.this, PrizeActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void onMapButtonClick(View view) {
        Intent intent = new Intent(GoalsActivity.this, MapActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void onQRCodeButtonClick(View view) {
        Intent intent = new Intent(GoalsActivity.this, QRCodeActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void onScoreButtonClick(View view) {
        Intent intent = new Intent(GoalsActivity.this, ScoreboardActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }


}