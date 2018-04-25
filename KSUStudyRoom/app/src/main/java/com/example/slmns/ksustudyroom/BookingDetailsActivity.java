package com.example.slmns.ksustudyroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BookingDetailsActivity extends AppCompatActivity {

    private String firstName;
    private String lastName;
    private String bookingEmail;
    Button submitButton;

    public String getFirstName() { return this.firstName; }
    public String getLastName(){return this.lastName;}
    public String getBookingEmail(){return this.bookingEmail;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        submitButton = findViewById(R.id.submitBookingButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent confirmationIntent = new Intent(getApplicationContext(), BookingConfirmationActivity.class);
                startActivity(confirmationIntent);
            }
        });
    }
}
