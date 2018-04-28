package com.example.slmns.ksustudyroom;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;


/**
 * Handles the login page.
 */
public class LogInActivity extends AppCompatActivity {

    //public final String BASE_URL = "http://ksustudyroom.azurewebsites.net/api";
    //RegisterActivity signUp = new RegisterActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);



        final EditText loginUsername = (EditText) findViewById(R.id.loginUsernameText);
        final EditText loginPassword = (EditText) findViewById(R.id.loginPasswordText);
        final Button loginButton = (Button) findViewById(R.id.kennesawBookingButton);
        final TextView registerLink = (TextView) findViewById(R.id.loginRegisterTextView);
        final TextView homePageLink = (TextView) findViewById(R.id.loginToHomeLink);
        final TextView invalidLoginUsernNameLabel = (TextView) findViewById(R.id.invalidLoginUserNameLabel);
        final TextView invalidLoginPasswordLabel = (TextView) findViewById(R.id.invalidLoginPasswordLabel);
        final TextView toStudyGroupLink = (TextView) findViewById(R.id.toStudyGroupLink);
        final String url = "http://ksustudyroom.azurewebsites.net/api/users/getall";
        final String body = "login?Username="+loginUsername.getText().toString()+"&Password="+loginPassword.getText().toString();
        final LoginTask loginTask = new LoginTask();
        JSONArray actualResults = new JSONArray();

        final String jsonString;

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean missingField = false;
                JSONArray loginInfo = null;



                /*AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        String userName= loginUsername.getText().toString();
                        String password= loginPassword.getText().toString();
                        //get all users api call
                        try {
                            URL url = new URL("http://ksustudyroom.azurewebsites.net/api/users/login?Username="+userName+"&Password="+password);
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

                            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(conn.getInputStream()));
                            String inputLine;
                            StringBuffer response = new StringBuffer();
                            while ((inputLine = in.readLine()) != null) {
                                response.append(inputLine);
                            }
                            in.close();
                            //print in String
                            System.out.println(response.toString());

                             JSONArray jsonArray = new JSONArray(response);
                             loginInfo= jsonArray;
                            //jsonArray.get(1).toString();



                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                });*/
                while (loginInfo == null) {
                    try {
                        loginInfo = loginTask.execute(loginUsername.getText().toString(), loginPassword.getText().toString()).get();

                        if (loginUsername.getText().toString().isEmpty()) {
                            invalidLoginUsernNameLabel.setVisibility(View.VISIBLE);
                            missingField = true;
                        }
                        else {
                            invalidLoginUsernNameLabel.setVisibility(View.INVISIBLE);
                        }

                        if (loginPassword.getText().toString().isEmpty()) {
                            invalidLoginPasswordLabel.setVisibility(View.VISIBLE);
                            missingField = true;
                        }
                        else {
                            invalidLoginPasswordLabel.setVisibility(View.INVISIBLE);
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Intent homeIntent = new Intent(LogInActivity.this, HomeV2Activity.class);
                LogInActivity.this.startActivity(homeIntent);

            }
        });

        /**
         * Switches to the register page on click.
         */
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LogInActivity.this, RegisterActivity.class);
                LogInActivity.this.startActivity(registerIntent);
            }
        });

        toStudyGroupLink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent studyGroupIntent = new Intent(LogInActivity.this, StartStudyGroupActivity.class);
                LogInActivity.this.startActivity(studyGroupIntent);
            }
        });


    }

    public class LoginTask extends AsyncTask<String, String, JSONArray> {



        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;

        @Override
        protected JSONArray doInBackground(String... params){
            String stringUrl = params[0];
            JSONArray result = new JSONArray();
            String inputLine;
            String userName=params[0];
            String password=params[1];
                //get all users api call
                try {
                    URL url = new URL("http://ksustudyroom.azurewebsites.net/api/users/login?Username="+userName+"&Password="+password);
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
                    System.out.println(response.toString());

                    result = new JSONArray(response);
                    //loginInfo= jsonArray;
                    //jsonArray.get(1).toString();

                }
                catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                return result;
            }

        protected void onPostExecute(JSONArray result){
            super.onPostExecute(result);
        }
        }

    }

