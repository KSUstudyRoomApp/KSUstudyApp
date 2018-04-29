package com.example.slmns.ksustudyroom;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class StudyGroupActivityV2 extends AppCompatActivity {
    private Button addStudyGroupButton;
    private EditText editGroupNameText;

    private ListView studyGroupListView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> listOfStudyGroups = new ArrayList<>();
    private String name;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("StudyGroups");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_group_v2);

        addStudyGroupButton = findViewById(R.id.addStudyGroupButton);
        editGroupNameText = findViewById(R.id.studyGroupNameEditText);
        studyGroupListView = findViewById(R.id.studyGroupListView);

        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listOfStudyGroups);
        studyGroupListView.setAdapter(arrayAdapter);
        this.requestUserName();

        addStudyGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //updates firebase database chatroom
              /*  Map<String,Object> map = new HashMap<String,Object>();
                map.put(editGroupNameText.getText().toString(),"");
                root.updateChildren(map);*/
              Intent createStudyGroupIntent = new Intent(getApplicationContext(), GroupActivity.class);
              startActivity(createStudyGroupIntent);
            }
        });

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //goes through the study groups database and reads it.
                Set<String> set = new HashSet<>();
                Iterator i = dataSnapshot.getChildren().iterator();
                while(i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());
                }
                listOfStudyGroups.clear();
                listOfStudyGroups.addAll(set);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        this.studyGroupListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent chatRoomIntent = new Intent(getApplicationContext(), PublicMessagingActivity.class);
                chatRoomIntent.putExtra("room_name",((TextView)view).getText().toString());
                chatRoomIntent.putExtra("username",name);
                startActivity(chatRoomIntent);
            }
        });
    }


    private void requestUserName() {
        /// TODO: 4/29/2018 Figure out what to initialize the user's chat name too. 
        this.name = "StaticName";
       /* AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter name:");

        final EditText input_field = new EditText(this);

        builder.setView(input_field);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                name = input_field.getText().toString();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                requestUserName();
            }
        });

        builder.show();*/
    }
}
