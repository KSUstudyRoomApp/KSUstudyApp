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

public class GroupActivity extends AppCompatActivity {

    private Button createStudGroupButton;
    private Button leaveGroupButton;
    private ListView groupListView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> listOfUsersGroups = new ArrayList<>();
    private DatabaseReference studyGroupReference = FirebaseDatabase.getInstance().getReference().child("StudyGroups");
    private String groupName;
    private ArrayList<String> listOfGroupMembers = new ArrayList<>();
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_view);

        this.createStudGroupButton = findViewById(R.id.addMemberButton);
        this.leaveGroupButton = findViewById(R.id.leaveGroupButton);
        this.groupListView = findViewById(R.id.groupListView);

        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listOfUsersGroups);
        groupListView.setAdapter(arrayAdapter);
        this.username = getIntent().getExtras().get("username").toString();

        this.createStudGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               requestGroupName();
            }
        });

        studyGroupReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //goes through the study groups database and reads it.
                Set<String> set = new HashSet<>();
                Iterator i = dataSnapshot.getChildren().iterator();
                while(i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());
                }
                listOfUsersGroups.clear();
                listOfUsersGroups.addAll(set);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        this.groupListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent membersIntent = new Intent(getApplicationContext(), AddMembersActivity.class);
                membersIntent.putExtra("group_name",((TextView)view).getText().toString());
                membersIntent.putExtra("username",username);
                startActivity(membersIntent);
            }
        });

    }

    private void requestGroupName(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Enter group name:");

        final EditText input_groupNameField = new EditText(this);

        builder.setView(input_groupNameField);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                groupName = input_groupNameField.getText().toString();
                  Map<String,Object> map = new HashMap<String,Object>();
                map.put(groupName,"");
                studyGroupReference.updateChildren(map);
            }
        });


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                //requestUserName();
            }
        });

        builder.show();
    }
}
