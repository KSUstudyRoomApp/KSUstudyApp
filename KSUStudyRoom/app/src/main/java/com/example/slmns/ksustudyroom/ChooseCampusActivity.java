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

        String real_id = getIntent().getExtras().getString("REAL_ID");
        final String new_real_id = real_id;


        //Select Campus - Kennesaw  Button
        Button kennesawCampusButton = (Button) findViewById(R.id.kennesawBtn);
        kennesawCampusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), KennesawCampus.class);
                //.putExtra("USER_ID_2", getIntent().getExtras().getString("USER_ID_1"));
                startIntent.putExtra("FIRST_NAME_2", getIntent().getExtras().getString("FIRST_NAME_1"));

                startIntent.putExtra("NEXT_REAL", new_real_id);
                System.out.println("the real id is"+ new_real_id);
                startActivity(startIntent);
            }
        });

        //Select Campus - Marietta  Button
        Button mariettaCampusButton = (Button) findViewById(R.id.mariettaBtn);
        mariettaCampusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MariettaCampus.class);
                //startIntent.putExtra("USER_ID_2", getIntent().getExtras().getString("USER_ID_1"));
                startIntent.putExtra("FIRST_NAME_2", getIntent().getExtras().getString("FIRST_NAME_1"));
                String real_id = getIntent().getExtras().getString("REAL_ID");
                System.out.println("the real id is"+ new_real_id);
                startActivity(startIntent);
            }
        });
    }
}
