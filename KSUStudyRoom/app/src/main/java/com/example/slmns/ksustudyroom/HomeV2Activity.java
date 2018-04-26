package com.example.slmns.ksustudyroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeV2Activity extends AppCompatActivity {
 Button reserveRoomButton;
 Button calendarButton;
 Button chatButton;
 Button studyGroupButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_v2);

        this.reserveRoomButton = findViewById(R.id.reserveRoomButton);
        this.calendarButton = findViewById(R.id.calendarButton);
        this.chatButton = findViewById(R.id.chatButton);
        this.studyGroupButton = findViewById(R.id.toStudyGroupsButton);

        reserveRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectCampusIntent = new Intent(getApplicationContext(), ChooseCampusActivity.class);
                startActivity(selectCampusIntent);
            }
        });

        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calendarIntent = new Intent(getApplicationContext(), CalendarActivity.class);
                startActivity(calendarIntent);
            }
        });

        studyGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent studyGroupIntent = new Intent(getApplicationContext(), StudyGroupActivityV2.class);
                startActivity(studyGroupIntent);
            }
        });



       /* chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chatIntent = new Intent(getApplicationContext(), PublicMessagingActivity.class);
                startActivity(chatIntent);
            }
        });*/
    }
}
