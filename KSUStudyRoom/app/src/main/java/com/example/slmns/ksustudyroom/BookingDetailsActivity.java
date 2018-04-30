package com.example.slmns.ksustudyroom;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookingDetailsActivity extends AppCompatActivity {
    TextView bookingDataTextString;
//    String user_id = getIntent().getExtras().getString("USER_ID_1");
  //  String room_name = getIntent().getExtras().getString("ROOM_NAME");
    //String timeslot_Id = getIntent().getExtras().getString("TIME_ID");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        //final String user_id = getIntent().getExtras().getString("USER_ID");



        //booking details from previous activity
        bookingDataTextString = (TextView) findViewById(R.id.bookingDetailsTextView);
        getBookingData();

        //Booking confirmation
        Button bookingConfirmation = (Button) findViewById(R.id.submitBookingButton);
        bookingConfirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), BookingConfirmationActivity.class);
                String roomsTestOnly = getIntent().getExtras().getString("ROOM_SELECTED");//GET ROOM SELECTEDID
                startIntent.putExtra("ROOM_SELECTED", roomsTestOnly);//pass room selected ID
                String roomFromPreviousActivityTest = getIntent().getExtras().getString("ROOM_NAME");
                System.out.println("THE USER ROOM IS "+roomFromPreviousActivityTest);
                String Timeid = getIntent().getExtras().getString("TIME_ID");
                System.out.println("THE USER TIME SLOT ID IS"+getIntent().getExtras().getString("TIME_ID"));

                //THIS VALUE RIGHT HERE IS NULL FIND A WAY TO GET THE USER ID FROM THE CHOOSECAMPUS ACTIVITY TO HERE
                //CREATING AN INTENT.PUTEXTRA() METHOD SEEMED TO WORK TO THE PAGE THAT IT POINTING TO ASSIGNMENT IN THE BUTTON LISTENERS FOR BOTH PAGES
                //ASSIGN THE VALUE TO USERID
                //IF YOU FIND THE ID AND PAST IT IN THE USER ID VARIABLE IT SHOULD WORK
                String userId=""; //THIS IS THE VARIABLE WE NEED TO GET THE USER ID TO new_real_id is the name of the variable that has the userid on the ChooseCampus page
                System.out.println("THE USER ID IS "+getIntent().getExtras().getString("REAL_ID"));

                JSONObject params2 = new JSONObject();
                try {
                    params2.put("roomId",roomFromPreviousActivityTest);
                    params2.put("timeslotId", Timeid);
                    params2.put("userId", userId);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }



                String tag_json_obj = "json_obj_req";

                String url = "http://ksustudyroom.azurewebsites.net/api/reservation/addreservation";



                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                        url, params2,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                //Log.d(TAG, response.toString());
                                //pDialog.hide();
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //VolleyLog.d(TAG, "Error: " + error.getMessage());
                        //pDialog.hide();
                    }
                }) {

                    @Override
                    public Map<String, String> getHeaders() {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        return headers;
                    }

                };

                AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);


                startActivity(startIntent);
            }
        });
    }

    private void getBookingData() {
        new Thread(new Runnable() {
            public void run() {
                final StringBuilder builder = new StringBuilder();
                //String roomName = getIntent().getExtras().getString("ROOM_SELECTED");
                //current date default
                //code
                String subheadBookingDataHeading = "BOOKING DETAILS";//

                //get sub heading text
                builder.append(subheadBookingDataHeading);
                builder.append("\n");

                //get it from previous activity and add it to this activity
                /*String dataFromPreviousActivity = getIntent().getExtras().getString("SELECTED_ROOM_AND_TIME");
                builder.append("\n");
                builder.append(dataFromPreviousActivity);*/
                String roomFromPreviousActivityTest = getIntent().getExtras().getString("ROOM_NAME");
                builder.append("\n");
                builder.append(roomFromPreviousActivityTest);

                String timeFromPreviousActivityTest = getIntent().getExtras().getString("TIME_SELECTED");
                builder.append("\n");
                builder.append(timeFromPreviousActivityTest);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bookingDataTextString.setText(builder.toString());
                        //setListAdapter();
                    }
                });
            }
        }).start();
    }
}
