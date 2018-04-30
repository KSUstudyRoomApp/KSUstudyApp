package com.example.slmns.ksustudyroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BookingConfirmationActivity extends AppCompatActivity {

    Button backHomeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmation);
        String user_id = getIntent().getExtras().getString("USER_ID_1");
        String room_name = getIntent().getExtras().getString("ROOM_NAME");
        String timeslot_Id = getIntent().getExtras().getString("TIME_ID");

        this.backHomeButton = findViewById(R.id.backToHomeButton);
        this.backHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





               Intent startIntent = new Intent(getApplicationContext(), HomeV2Activity.class);
                startIntent.putExtra("FIRST_NAME", getIntent().getExtras().getString("FIRST_NAME_BOOKING"));
                /*backHomeIntent.putExtra("FIRST_NAME", firstName);
                backHomeIntent.putExtra("EMAIL", email);
                backHomeIntent.putExtra("PASSWORD", password);
                backHomeIntent.putExtra("LAST_NAME", lastName);
                backHomeIntent.putExtra("PHONE", phone);
                backHomeIntent.putExtra("USERNAME", username);
                backHomeIntent.putExtra("USER_ID", id); */
                startActivity(startIntent);
            }
        });
    }
}
