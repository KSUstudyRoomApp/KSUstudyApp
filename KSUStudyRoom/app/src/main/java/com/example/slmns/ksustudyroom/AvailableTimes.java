package com.example.slmns.ksustudyroom;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class AvailableTimes extends AppCompatActivity {
    ListView availableTimesListView;
    ArrayAdapter<String> availableTimesGroupAdapter;
    ArrayList<String> data = new ArrayList<String>();
    TextView headingTextDescription;
    public String newString;
    ArrayList<String> timeID = new ArrayList<String>();


    private String selectedTime ;//frank to pass selected room id, selected time id to next activity - use REST API
    public String selectedTimeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_times);
        headingTextDescription = (TextView) findViewById(R.id.timeSlotTextView);

        final StringBuilder builder = new StringBuilder();



        String roomName = getIntent().getExtras().getString("ROOM_SELECTED");
        System.out.print("ROOM NAME IS"+roomName);

        //pass room selected intent
        /*Bundle roomData = getIntent().getExtras();
        if(roomData==null){
            return;
        }
        String appleMessage = roomData.getString("ROOM_SELECTED");
        final TextView roomName = (TextView) findViewById(R.id.textView4);
        roomName.setText(appleMessage);*/

        //get times
        //String jsonString = times.execute("").get()






        //data= new ArrayList<>();
        getAvailableTimes();
    }

    private void getAvailableTimes() {
        new Thread(new Runnable() {
            public void run() {
                final StringBuilder builder = new StringBuilder();
                headingTextDescription = (TextView) findViewById(R.id.timeSlotTextView);
                //times for current date by default
                String subheadTextAvailableTimes = "Available Times for:";
                builder.append(subheadTextAvailableTimes);
                builder.append("\n");

                //general way to do this
                /*String dataFromPreviousActivity = getIntent().getExtras().getString("STRING_I_NEED");
                builder.append("\n");
                builder.append(dataFromPreviousActivity);*/

                //from kennesaw/marietta campus page - DUP
                String roomName = getIntent().getExtras().getString("ROOM_SELECTED");
                builder.append(roomName);
                builder.append("\n");


                /*ArrayList<String> availableTimes = new ArrayList<String>();
                availableTimes.add("8am - 10am");
                availableTimes.add("10am - 12pm ");
                availableTimes.add("12pm - 2pm ");
                availableTimes.add("2pm - 4pm ");
                availableTimes.add("4pm - 6pm");
                availableTimes.add("6pm - 8pm");
                availableTimes.add("8pm - 10pm");
                for (String rooms : availableTimes) {
                    //data.add(rooms);
                }*/
                ArrayList<String> timeList = new ArrayList<String>();

                GetTimes times = new GetTimes();

                try {
                    String jsonString = times.execute(roomName).get();
                    JSONArray json = new JSONArray(jsonString);
                    System.out.println("THIS SHOULD PRINT OUT THE JSONARRAY"+json);
                    for(int i=0; i< json.length(); i++){
                        JSONObject jsonObject = json.getJSONObject(i);
                        timeList.add(jsonObject.optString("slots"));
                        timeID.add(jsonObject.optString("id"));
                        System.out.println("THIS IS A TIME"+timeList.get(i));
                        data.add(timeList.get(i));
                    }

                }
                catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
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
        availableTimesGroupAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        availableTimesListView = (ListView) findViewById(R.id.availableTimesListView);
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
                selectedTime = (String)parent.getItemAtPosition(position);
                selectedTimeId = timeID.get(position);
                System.out.println("THE ID OF THIS TIME IS" +selectedTimeId);

               // String selectedRm = getIntent().getExtras().getString("ROOM_SELECTED");
                //startIntent.putExtra("ROOM_SELECTED", selectedTime);
                //selectedRoomAndTimeIDs = selectedTime + " " + selectedRm;
                //startIntent.putExtra("SELECTED_ROOM_AND_TIME", selectedRoomAndTimeIDs);

                //String selectedRm = getIntent().getExtras().getString("ROOM_SELECTED");
                //startIntent.putExtra("ROOM_SELECTED", selectedTime);
                //selectedRoomAndTimeIDs = selectedTime + " " + selectedRm;
                startIntent.putExtra("ROOM_NAME", getIntent().getExtras().getString("ROOM_SELECTED"));
                startIntent.putExtra("TIME_SELECTED", selectedTime);
                startIntent.putExtra("TIME_ID", selectedTimeId);
                //startIntent.putExtra("USER_ID_4", getIntent().getExtras().getString("USER_ID_3"));
                startIntent.putExtra("FIRST_NAME_4", getIntent().getExtras().getString("FIRST_NAME_3"));
                startActivity(startIntent);
            }
        });
    }

    public class GetTimes extends AsyncTask<String, Void, String> {



        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;
        User userInfo = new User();

        @Override
        protected String doInBackground(String... params){
            String roomId = params[0];
            String result = "";
            String inputLine;
            String campus=params[0];

            //get all users api call
            try {
                URL url = new URL("http://ksustudyroom.azurewebsites.net/api/studyrooms/getavailabletime?roomId="+roomId);
                System.out.println(url);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("User-Agent", "Mozilla/5.0");
                conn.setRequestMethod("GET");
                int responseCode = conn.getResponseCode();
                System.out.println("\nSending 'POST' request to URL : " + url);
                System.out.println("Response Code : " + responseCode);

                //conn.setDoOutput(true);
                //OutputStream os = conn.getOutputStream();
                //os.write("username=vdoe200".getBytes());
                //os.write("password=Test-ksuApp".getBytes());
                //os.flush();
                //os.close();

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                //String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                //print in String
                result = response.toString();
                System.out.println("THE OUTPUT OF THE THING IS "+ result);


                //System.out.println(result.toString());
                //loginInfo= jsonArray;
                //jsonArray.get(1).toString();

            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        protected void onPostExecute(String result){
            super.onPostExecute(result);
        }
    }
}
