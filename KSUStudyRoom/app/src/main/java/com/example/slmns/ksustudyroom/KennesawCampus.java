package com.example.slmns.ksustudyroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class KennesawCampus extends AppCompatActivity {
    private WebView webView;
    ListView kennesawListView;
    ArrayList<String> data ;
    ArrayAdapter<String> kennesawRoomGroupAdapter;
    TextView headingTextDescription;
    Button bookingDetailsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kennesaw_campus);

        //scrap heading text description
        headingTextDescription = (TextView) findViewById(R.id.textView);
        data = new ArrayList<String>();
        bookingDetailsButton = findViewById(R.id.kennesawBookingButton);

        //get rooms
        getAvailableRooms();
        
        bookingDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), BookingDetailsActivity.class);
                startActivity(startIntent);
            }
        });
    }
    
    private void getAvailableRooms() {
        new Thread(new Runnable() {
            public void run() {
                final StringBuilder builder = new StringBuilder();
                //current date default
                //code
                String subheadTextKennesaw = "Available study rooms at Kennesaw Library.";
                //get sub heading text
                builder.append(subheadTextKennesaw);
                builder.append("\n");

                //add name of button clicked from previous activity
                final String selectedCampus = "Kennesaw";

                ArrayList<String> kennesawRooms = new ArrayList<String>();
                kennesawRooms.add("Group Study Room 302J ");
                kennesawRooms.add("Group Study Room 302H ");
                kennesawRooms.add("Group Study Room 302G ");
                kennesawRooms.add("Group Study Room 302E ");
                kennesawRooms.add("Group Study Room 302D ");
                kennesawRooms.add("Group Study Room 302C ");
                kennesawRooms.add("Group Study Room 302B ");
                kennesawRooms.add("Group Study Room 302A ");
                kennesawRooms.add("Group Study Room 122 ");
                kennesawRooms.add("Group Study Room 126");
                kennesawRooms.add("Collab Tech Room 127");
                kennesawRooms.add("Collab Tech Room 128");
                kennesawRooms.add("Collab Tech Room 129");
                kennesawRooms.add("Collab Tech Room 130");
                kennesawRooms.add("Collab Tech Room 131");
                for (String rooms : kennesawRooms) {
                    data.add(rooms);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        headingTextDescription.setText(builder.toString());
                        setListAdapter();
                    }
                });
            }
        }).start();
    }
    
    private void  setListAdapter(){
        kennesawRoomGroupAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        kennesawListView = (ListView) findViewById(R.id.listView);
        kennesawListView.setAdapter(kennesawRoomGroupAdapter);
        clickListener();
        roomClickListener();
    }

    //click listener shows preview of item clicked
    private void clickListener(){
        kennesawListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String room = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(KennesawCampus.this, room, Toast.LENGTH_LONG).show();
                    }
                }

        );
    }
    
    //Roomclick listener shows hours available for room clicked on
    private void roomClickListener(){
        kennesawListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent startIntent = new Intent(getApplicationContext(), BookingDetailsActivity.class);
                final String selectedRoom = "Group Study Room 302J KSU ROOM TEST";
                startIntent.putExtra("ROOM_SELECTED",selectedRoom);
                startActivity(startIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kennesaw_campus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
