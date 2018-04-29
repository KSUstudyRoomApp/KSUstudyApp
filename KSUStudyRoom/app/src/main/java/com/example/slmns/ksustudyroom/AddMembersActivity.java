package com.example.slmns.ksustudyroom;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddMembersActivity extends AppCompatActivity {
    private String group_name;
    private Button addMemberButton;
    private String memberName;
    //private DatabaseReference memberReference = FirebaseDatabase.getInstance().getReference().child("StudyGroups").child("Member");
    private DatabaseReference memberReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_members);

        this.addMemberButton = findViewById(R.id.addMemberButton);

        group_name = getIntent().getExtras().get("group_name").toString();
        setTitle("StudyGroup: " + group_name);

        //references the active chat room
        memberReference = FirebaseDatabase.getInstance().getReference().child("StudyGroups").child(group_name).child("Member");

        this.addMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestMemberName();
            }
        });


    }

    private void requestMemberName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Enter member name:");

        final EditText input_memberNameField = new EditText(this);

        builder.setView(input_memberNameField);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                memberName = input_memberNameField.getText().toString();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(memberName, "");
                memberReference.updateChildren(map);
            }
        });

        builder.show();
    }

}
