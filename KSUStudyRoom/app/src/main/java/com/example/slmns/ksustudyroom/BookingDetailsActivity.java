package com.example.slmns.ksustudyroom;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class BookingDetailsActivity extends AppCompatActivity {
    TextView bookingDataTextString;
//    String user_id = getIntent().getExtras().getString("USER_ID_1");
  //  String room_name = getIntent().getExtras().getString("ROOM_NAME");
    //String timeslot_Id = getIntent().getExtras().getString("TIME_ID");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        //final String user_id = getIntent().getExtras().getString("USER_ID");



        //booking details from previous activity
        bookingDataTextString = (TextView) findViewById(R.id.bookingDetailsTextView);
        getBookingData();

        //Booking confirmation
        Button bookingConfirmation = (Button) findViewById(R.id.submitBookingButton);
        bookingConfirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), BookingConfirmationActivity.class);
                String roomsTestOnly = getIntent().getExtras().getString("ROOM_SELECTED");//GET ROOM SELECTEDID
                startIntent.putExtra("ROOM_SELECTED", roomsTestOnly);//pass room selected ID
                String roomFromPreviousActivityTest = getIntent().getExtras().getString("ROOM_NAME");
                System.out.println("THE USER ROOM IS "+roomFromPreviousActivityTest);
                System.out.println("THE USER TIME SLOT ID IS"+getIntent().getExtras().getString("TIME_ID"));

                System.out.println("THE USER ID IS "+getIntent().getExtras().getString("REAL_ID"));


                startActivity(startIntent);
            }
        });
    }

    private void getBookingData() {
        new Thread(new Runnable() {
            public void run() {
                final StringBuilder builder = new StringBuilder();
                //String roomName = getIntent().getExtras().getString("ROOM_SELECTED");
                //current date default
                //code
                String subheadBookingDataHeading = "BOOKING DETAILS";//

                //get sub heading text
                builder.append(subheadBookingDataHeading);
                builder.append("\n");

                //get it from previous activity and add it to this activity
                /*String dataFromPreviousActivity = getIntent().getExtras().getString("SELECTED_ROOM_AND_TIME");
                builder.append("\n");
                builder.append(dataFromPreviousActivity);*/
                String roomFromPreviousActivityTest = getIntent().getExtras().getString("ROOM_NAME");
                builder.append("\n");
                builder.append(roomFromPreviousActivityTest);

                String timeFromPreviousActivityTest = getIntent().getExtras().getString("TIME_SELECTED");
                builder.append("\n");
                builder.append(timeFromPreviousActivityTest);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bookingDataTextString.setText(builder.toString());
                        //setListAdapter();
                    }
                });
            }
        }).start();
    }
}
