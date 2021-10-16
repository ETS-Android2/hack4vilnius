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
    String[] goals = {"Movie ticket", "Coffee discount", "More stuff"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        listView = (ListView) findViewById(R.id.listViewGoalsList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, goals);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " selected", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(GoalsActivity.this, UserDashboardActivity.class);
                intent.putExtra("goal", position);
                startActivity(intent);
            }
        });
    }


}