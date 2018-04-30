package com.example.slmns.ksustudyroom;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
        String reservedRoom;

        String campus = "kennesaw";
        GetRooms rooms = new GetRooms();
        ArrayList<String> roomList = new ArrayList<String>();

        final StringBuilder builder = new StringBuilder();
        //current date default
        //code
        String subheadTextKennesaw = "Available study rooms at Kennesaw Library.";
        //get sub heading text
        builder.append(subheadTextKennesaw);
        builder.append("\n");

        //get rooms

       //roomList.add(rooms);
        try {
            String jsonString = rooms.execute(campus).get();
            JSONArray json = new JSONArray(jsonString);
            System.out.println("THIS SHOULD PRINT OUT THE JSONARRAY"+json);
            for(int i=0; i< json.length(); i++){
                JSONObject jsonObject = json.getJSONObject(i);
                roomList.add(jsonObject.optString("roomName"));
                System.out.println(roomList.get(i));
                data.add(roomList.get(i));
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //getAvailableRooms();
        setListAdapter();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                headingTextDescription.setText(builder.toString());
                setListAdapter();
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

        roomClickListener();
    }
    String room1;
    
    //Roomclick listener shows hours available for room clicked on
    private void roomClickListener(){
        kennesawListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent startIntent = new Intent(getApplicationContext(), AvailableTimes.class);
                String room = String.valueOf(parent.getItemAtPosition(position));
                room1 = (String)parent.getItemAtPosition(position);
                System.out.print("ROOM1"+ room1);
                //Intent startIntent = new Intent(getApplicationContext(), BookingDetailsActivity.class);
                startIntent.putExtra("ROOM_SELECTED", room);
                startActivity(startIntent);
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

    public class GetRooms extends AsyncTask<String, Void, String> {



        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;
        User userInfo = new User();

        @Override
        protected String doInBackground(String... params){
            String stringUrl = params[0];
            String result = "";
            String inputLine;
            String campus=params[0];

            //get all users api call
            try {
                URL url = new URL("http://ksustudyroom.azurewebsites.net/api/studyrooms/getrooms?campus="+campus);
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
