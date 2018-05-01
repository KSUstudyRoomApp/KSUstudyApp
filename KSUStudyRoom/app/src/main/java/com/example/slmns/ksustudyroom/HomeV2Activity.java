package com.example.slmns.ksustudyroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeV2Activity extends AppCompatActivity {
 Button reserveRoomButton;
 Button calendarButton;
 Button chatButton;
 Button studyGroupButton;
 TextView greeting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_v2);
        final Session session = new Session(HomeV2Activity.this);
        User user = new User();

        String firstName = getIntent().getExtras().getString("FIRST_NAME");
        String email = getIntent().getExtras().getString("EMAIL");
        String lastName = getIntent().getExtras().getString("LAST_NAME");
        String password = getIntent().getExtras().getString("PASSWORD");
        String phone = getIntent().getExtras().getString("PHONE");
        final String username = getIntent().getExtras().getString("USERNAME");
        final String user_id = getIntent().getExtras().getString("USER_ID");

        final String Real_id = user_id;




        user.setFirstName(firstName);



        this.reserveRoomButton = findViewById(R.id.reserveRoomButton);
        this.calendarButton = findViewById(R.id.calendarButton);
        this.chatButton = findViewById(R.id.chatButton);
        this.studyGroupButton = findViewById(R.id.toStudyGroupsButton);
        this.greeting = findViewById(R.id.greeting);

        greeting.setText("Hello "+ firstName);
        System.out.println("THE REAL ID IS "+Real_id);


        reserveRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectCampusIntent = new Intent(getApplicationContext(), ChooseCampusActivity.class);
                //selectCampusIntent.putExtra("USER_ID_1", user_id);
                selectCampusIntent.putExtra("REAL_ID", Real_id);
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
                studyGroupIntent.putExtra("USERNAME", username);
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
