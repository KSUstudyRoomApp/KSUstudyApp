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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

        showRooms();
        bookingDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), BookingDetailsActivity.class);
                startActivity(startIntent);
            }
        });

    }
    private void showRooms() {
        new Thread(new Runnable() {
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    //current date
                    Document doc = Jsoup.connect("http://kennesaw.libcal.com/rooms_acc.php?gid=12348").get();
                    //Custom date used here to show available future rooms since current date is all blacked out. in case below 4/23/18
                    // Document doc = Jsoup.connect("http://kennesaw.libcal.com/rooms_acc.php?gid=12348&d=2018-04-23&cap=0").get();
                    // add custom date
                  /*  String yy = "2018";
                    String mm = "04";
                    String dd = "23";
                    String yymmdd = yy+'-'+mm+'-'+dd;
                   Document doc = Jsoup.connect("http://kennesaw.libcal.com/rooms_acc.php?gid=12348&d="+yymmdd+"&cap=0").get();*/

                    //get sub heading text
                    Element subheadText = doc.getElementById("s-lc-rm-acc-group-desc");
                    builder.append(subheadText.text());
                    builder.append("\n");
                    /*builder.append("date: " + d1);*/

                    //4/22/18 10:15 PM
                    Element div1 = doc.getElementById("s-lc-rm-right");
                    Elements form = doc.select("form");
                    Elements fieldsets = form.select("fieldset");
                    for(Element f : fieldsets){
                        Elements inputData = f.select("input");
                        for (Element hiddenInputs : inputData) {
                            if (hiddenInputs.attr("value").equals("30")) {//do nothing
                            } else if (hiddenInputs.attr("type").equals("hidden")) {
                                data.add(hiddenInputs.attr("value").toString());
                            }
                        }
                    }

                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
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
    //Scrape KSU studyrooms WEBSITE
   /* private void showRooms() {
        new Thread(new Runnable() {
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    //Document doc = Jsoup.connect("http://kennesaw.libcal.com/rooms_acc.php?gid=12348").get();
                    //Custom date used here to show available future rooms since current date is all blacked out. in case below 4/23/18
                    Document doc = Jsoup.connect("http://kennesaw.libcal.com/rooms_acc.php?gid=12348&d=2018-04-23&cap=0").get();

                    //get sub heading text
                    Element subheadText = doc.getElementById("s-lc-rm-acc-group-desc");
                    builder.append(subheadText.text());

                    //4/22/18 10:15 PM
                    Element div1 = doc.getElementById("s-lc-rm-right");
                    Elements form = doc.select("form");
                    Elements fieldsets = form.select("fieldset");
                    for(Element f : fieldsets){
                        //legend
                        Elements legendh2 = f.select("legend > h2");

                        Elements description = f.select("div");
                        String divs = description.text();
                        data.add(legendh2.text() + "\n" + divs);  //add h2
                    }

                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
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
    }*/
    private void  setListAdapter(){
        kennesawRoomGroupAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        kennesawListView = (ListView) findViewById(R.id.listView);
        kennesawListView.setAdapter(kennesawRoomGroupAdapter);
        clickListener();
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
    /*@Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        }else {
            super.onBackPressed();
        }
    }*/

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
