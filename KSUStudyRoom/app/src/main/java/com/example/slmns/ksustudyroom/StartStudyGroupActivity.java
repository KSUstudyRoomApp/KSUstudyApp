package com.example.slmns.ksustudyroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Handles the page for creating study groups.
 */
public class StartStudyGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_study_group);

        final Button addMemberButton  = findViewById(R.id.addMemberButton);
        final TextView addMemberTextBox = findViewById(R.id.addMemberTextBox);

        /**
         * Add the member and clear the user's name when clicking the add memeber button.
         */
        addMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMemberTextBox.setText("");
            }
        });


    }
}
