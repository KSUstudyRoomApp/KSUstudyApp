package com.example.slmns.ksustudyroom;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CalendarView;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {
    public int day;
    public int theyear;
    public int themonth;
    public boolean dateIsClicked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        final CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        final WebView webView = findViewById(R.id.ksuwebview);
        final Button addEventButton = (Button) findViewById(R.id.addEventButton) ;
        final Button viewEventButton = (Button) findViewById(R.id.viewEvent);
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);
      /*  viewEventButton.setVisibility(View.GONE);
        addEventButton.setVisibility(View.GONE);*/

        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chooseCampusIntent = new Intent(getApplicationContext(), ChooseCampusActivity.class);
                startActivity(chooseCampusIntent);
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override

            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                dateIsClicked = true;
                day = dayOfMonth;
                themonth = month;
                theyear = year;

                /*if(dateIsClicked ) {
                    viewEventButton.setVisibility(View.VISIBLE);
                    addEventButton.setVisibility(View.VISIBLE);

                    *//*addEventButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setContentView(R.layout.activity_choose_campus);
                        }
                    });*//*
                }*/

            }
        });
    }
}