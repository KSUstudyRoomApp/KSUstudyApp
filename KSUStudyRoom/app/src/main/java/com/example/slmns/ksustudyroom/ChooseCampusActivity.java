package com.example.slmns.ksustudyroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.app.Activity;

public class ChooseCampusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_campus);

        //Select Campus - Kennesaw  Button
        Button kennesawCampusButton = (Button) findViewById(R.id.kennesawBtn);
        kennesawCampusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), KennesawCampus.class);
                startActivity(startIntent);
            }
        });

        //Select Campus - Marietta  Button
        Button mariettaCampusButton = (Button) findViewById(R.id.mariettaBtn);
        mariettaCampusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MariettaCampus.class);
                startActivity(startIntent);
            }
        });
    }
}
