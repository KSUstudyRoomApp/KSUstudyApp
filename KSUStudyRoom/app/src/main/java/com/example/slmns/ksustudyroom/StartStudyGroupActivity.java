package com.example.slmns.ksustudyroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Handles the page for creating study groups.
 */
public class StartStudyGroupActivity extends AppCompatActivity {

    private ArrayList groupMembers;
    public ArrayList getgroupMembers() { return this.groupMembers; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.groupMembers = new ArrayList();

        RegisterActivity registerActivity = new RegisterActivity();

        //String username = registerActivity.getUserName();

       // String username2 = RegisterActivity.getUserName();
       // String username3 = RegisterActivity.userName;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_study_group);

        final Button addMemberButton  = findViewById(R.id.addMemberButton);
        final Button submitStudyGroupButton = findViewById(R.id.submitStudyGroupButton);
        final Button cancelButton = findViewById(R.id.cancelStudyGroupButton);
        final TextView addMemberTextBox = findViewById(R.id.addMemberTextBox);
        final TextView confirmAddedUserText = findViewById(R.id.confirmAddedUserText);



       // Add the member and clear the user's name when clicking the add memeber button.
        addMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* groupMembers.add(addMemberTextBox.getText());
                confirmAddedUserText.setText(addMemberTextBox.getText() + " will be added to the group");
                addMemberTextBox.setText("");*/
            }
        });

        submitStudyGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




    }
}
