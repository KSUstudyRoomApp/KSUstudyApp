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
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AddMembersActivity extends AppCompatActivity {
    private ArrayAdapter<String> arrayAdapter;
    private String group_name;
    private Button addMemberButton;
    private String memberName;
    private ArrayList<String> listOfMembers = new ArrayList<>();
    private ListView memberListView;
    private String username;
    //private DatabaseReference memberReference = FirebaseDatabase.getInstance().getReference().child("StudyGroups").child("Member");
    private DatabaseReference memberReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_members);

        this.addMemberButton = findViewById(R.id.addMemberButton);
        this.memberListView = findViewById(R.id.memberListView);

        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listOfMembers);
        memberListView.setAdapter(arrayAdapter);

        this.username = getIntent().getExtras().get("username").toString();
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

        memberReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //goes through the study groups database and reads it.
                Set<String> set = new HashSet<>();
                Iterator i = dataSnapshot.getChildren().iterator();
                while(i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());
                }
                listOfMembers.clear();
                listOfMembers.addAll(set);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        this.memberListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent chatRoomIntent = new Intent(getApplicationContext(), PrivateMessagingActivity.class);
                chatRoomIntent.putExtra("room_name",((TextView)view).getText().toString());
                chatRoomIntent.putExtra("username",username);
                startActivity(chatRoomIntent);
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
                listOfMembers.add(memberName);
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(memberName, "");
                memberReference.updateChildren(map);
            }
        });

        builder.show();
    }

}
