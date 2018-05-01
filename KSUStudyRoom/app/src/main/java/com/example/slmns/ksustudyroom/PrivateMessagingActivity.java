package com.example.slmns.ksustudyroom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PrivateMessagingActivity extends AppCompatActivity {

    private Button sendButton;
    private EditText inputMessage;
    private TextView chatConversation;

    private String user_name,room_name;
    private DatabaseReference root;
    private String temp_key;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceStae){
        super.onCreate(savedInstanceStae);
        setContentView(R.layout.public_chatv2);

        this.sendButton = findViewById(R.id.publicSendButton);
        this.inputMessage = findViewById(R.id.publicMsgInputText);
        this.chatConversation = findViewById(R.id.publicConversationText);

        user_name = getIntent().getExtras().get("username").toString();
        room_name = getIntent().getExtras().get("room_name").toString();
        setTitle("Room - "+room_name);

        //references the active chat room
        root = FirebaseDatabase.getInstance().getReference().child("Private").child(room_name).child("chat");

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<String, Object>();
                temp_key = root.push().getKey();
                root.updateChildren(map);

                DatabaseReference mesage_root = root.child(temp_key);//random chatroom identifier
                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put("name", user_name);
                map2.put("msg", inputMessage.getText().toString());

                mesage_root.updateChildren(map2);

                inputMessage.getText().clear();

            }
        });

        //post messages on screen
        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    private String chat_msg,chat_user_name;
    private void append_chat_conversation(DataSnapshot dataSnapshot) {
        Iterator i = dataSnapshot.getChildren().iterator();

        while(i.hasNext()){
            chat_msg =(String) ((DataSnapshot)i.next()).getValue();
            chat_user_name =(String) ((DataSnapshot)i.next()).getValue();

            this.chatConversation.append(chat_user_name +" : "+chat_msg + " \n");
        }
    }

}
