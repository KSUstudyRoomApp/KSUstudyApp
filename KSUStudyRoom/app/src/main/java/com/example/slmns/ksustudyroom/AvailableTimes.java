package com.example.slmns.ksustudyroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AvailableTimes extends AppCompatActivity {
    ListView availableTimesListView;
    ArrayList<String> data ;
    ArrayAdapter<String> availableTimesGroupAdapter;
    TextView headingTextDescription;
    public String newString;


    private String selectedTime ;//frank to pass selected room id, selected time id to next activity - use REST API
    public String selectedRoomAndTimeIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_times);

        //pass room selected intent
        /*Bundle roomData = getIntent().getExtras();
        if(roomData==null){
            return;
        }
        String appleMessage = roomData.getString("ROOM_SELECTED");
        final TextView roomName = (TextView) findViewById(R.id.textView4);
        roomName.setText(appleMessage);*/

        //get times
        getAvailableTimes();
    }

    private void getAvailableTimes() {
        new Thread(new Runnable() {
            public void run() {
                final StringBuilder builder = new StringBuilder();
                //times for current date by default
                String subheadTextAvailableTimes = "Available Times for:";
                builder.append(subheadTextAvailableTimes);
                builder.append("\n");
                newString = getIntent().getExtras().getString("ROOM_SELECTED");
                builder.append(newString);
                builder.append("\n");

                //general way to do this
                /*String dataFromPreviousActivity = getIntent().getExtras().getString("STRING_I_NEED");
                builder.append("\n");
                builder.append(dataFromPreviousActivity);*/

                //from kennesaw/marietta campus page - DUP
                String roomName = getIntent().getExtras().getString("ROOM_SELECTED");
                builder.append(roomName);
                builder.append("\n");


                ArrayList<String> availableTimes = new ArrayList<String>();
                availableTimes.add("8am - 10am");
                availableTimes.add("10am - 12pm ");
                availableTimes.add("12pm - 2pm ");
                availableTimes.add("2pm - 4pm ");
                availableTimes.add("4pm - 6pm");
                availableTimes.add("6pm - 8pm");
                availableTimes.add("8pm - 10pm");
                for (String rooms : availableTimes) {
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

    //click listener shows preview of item clicked
    private void clickListener(){
        availableTimesListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String room = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(AvailableTimes.this, room, Toast.LENGTH_LONG).show();
                    }
                }

        );
    }

    private void  setListAdapter(){
        availableTimesGroupAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        //availableTimesListView = (ListView) findViewById(R.id.listView2);
        availableTimesListView.setAdapter(availableTimesGroupAdapter);
        //clickListener();
        roomClickListener();
    }

    //listens for roomClick
    private void roomClickListener(){
        availableTimesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent startIntent = new Intent(getApplicationContext(), BookingDetailsActivity.class);
                selectedTime = "10-12PM TEST!";

                String selectedRm = getIntent().getExtras().getString("ROOM_SELECTED");
                startIntent.putExtra("ROOM_SELECTED", selectedTime);
                selectedRoomAndTimeIDs = selectedTime + " " + selectedRm;
                startIntent.putExtra("SELECTED_ROOM_AND_TIME", selectedRoomAndTimeIDs);

                //String selectedRm = getIntent().getExtras().getString("ROOM_SELECTED");
                //startIntent.putExtra("ROOM_SELECTED", selectedTime);
                //selectedRoomAndTimeIDs = selectedTime + " " + selectedRm;
                startIntent.putExtra("TIME_SELECTED", selectedTime);
                startActivity(startIntent);
            }
        });
    }
}
