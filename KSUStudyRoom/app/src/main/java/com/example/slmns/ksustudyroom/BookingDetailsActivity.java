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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        //booking details from previous activity
        bookingDataTextString = (TextView) findViewById(R.id.textView4);
        getBookingData();

        //Booking confirmation
        Button bookingConfirmation = (Button) findViewById(R.id.reserveRoomBtn);
        bookingConfirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), BookingConfirmationActivity.class);
                String bookingDataTextStringCurrent = getIntent().getExtras().getString("SELECTED_ROOM_AND_TIME");
                //dataFromPreviousActivity;//"Test ROOM A Test TIME 10-12PM TEST!";
                startIntent.putExtra("ROOM_AND_TIMES_STRING", bookingDataTextStringCurrent);
                String roomsTestOnly = getIntent().getExtras().getString("ROOM_SELECTED");//GET ROOM SELECTEDID
                startIntent.putExtra("ROOM_SELECTED", roomsTestOnly);//pass room selected ID

                startActivity(startIntent);
            }
        });
    }

    private void getBookingData() {
        new Thread(new Runnable() {
            public void run() {
                final StringBuilder builder = new StringBuilder();
                //current date default
                //code
                String subheadBookingDataHeading = "";//

                //get sub heading text
                builder.append(subheadBookingDataHeading);
                builder.append("\n");

                //get it from previous activity and add it to this activity
                /*String dataFromPreviousActivity = getIntent().getExtras().getString("SELECTED_ROOM_AND_TIME");
                builder.append("\n");
                builder.append(dataFromPreviousActivity);*/
                String roomFromPreviousActivityTest = getIntent().getExtras().getString("ROOM_SELECTED");
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
