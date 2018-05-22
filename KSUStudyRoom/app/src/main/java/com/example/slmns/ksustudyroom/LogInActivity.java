package com.example.slmns.ksustudyroom;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


/**
 * Handles the login page.
 */
public class LogInActivity extends AppCompatActivity {
    String loginInfo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        final EditText loginUsername = (EditText) findViewById(R.id.loginUsernameText);
        final EditText loginPassword = (EditText) findViewById(R.id.loginPasswordText);
        final Button loginButton = (Button) findViewById(R.id.kennesawBookingButton);
        final TextView registerLink = (TextView) findViewById(R.id.loginRegisterTextView);
        final TextView invalidLoginUsernNameLabel = (TextView) findViewById(R.id.invalidLoginUserNameLabel);
        final TextView invalidLoginPasswordLabel = (TextView) findViewById(R.id.invalidLoginPasswordLabel);
        final TextView wrongPassword = findViewById(R.id.invalidPassword);
        final Session session = new Session(LogInActivity.this);


        loginButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                boolean missingField = false;


                String firstName;
                String email;
                String lastName;
                String username;
                String id;
                String phone;
                String password;
                boolean lemmeIn = false;

                User userInfo = new User();
                JSONObject params2 = new JSONObject();



                    loginInfo = getJSON(loginUsername.getText().toString(), loginPassword.getText().toString());

                System.out.println("LOGIN INFO IS SUPPOSE TO BE NULL HERE" + loginInfo);


                    if (loginUsername.getText().toString().isEmpty()) {
                        invalidLoginUsernNameLabel.setVisibility(View.VISIBLE);
                        missingField = true;
                    } else {
                        invalidLoginUsernNameLabel.setVisibility(View.INVISIBLE);
                    }

                    if (loginPassword.getText().toString().isEmpty()) {
                        invalidLoginPasswordLabel.setVisibility(View.VISIBLE);
                        missingField = true;
                    } else {
                        invalidLoginPasswordLabel.setVisibility(View.INVISIBLE);
                    }

                if (loginInfo.contains("id")) {

                    JSONObject json = null;
                    try {
                        json = new JSONObject(loginInfo);

                        //issa test

                        userInfo.setEmail(json.getString("email"));
                        userInfo.setPassword(json.getString("password"));
                        userInfo.setFirstName(json.getString("firstName"));
                        userInfo.setLastName(json.getString("lastName"));
                        userInfo.setPhone(json.getString("phone"));
                        userInfo.setUsername(json.getString("username"));
                        userInfo.setId(json.getString("id"));

                        session.setId(json.getString("id"));

                        session.setFirstName(json.getString("firstName"));

                        session.setUserName(json.getString("username"));

                        email = userInfo.getEmail();
                        firstName = userInfo.getFirstName();
                        password = userInfo.getPassword();
                        lastName = userInfo.getLastName();
                        phone = userInfo.getPhone();
                        username = userInfo.getUsername();
                        id = userInfo.getId();

                        System.out.println("THE USER ID OF THE THINGY THING THING IS" + id);


                        Intent homeIntent = new Intent(LogInActivity.this, HomeV2Activity.class);
                        homeIntent.putExtra("FIRST_NAME", firstName);
                        homeIntent.putExtra("EMAIL", email);
                        homeIntent.putExtra("PASSWORD", password);
                        homeIntent.putExtra("LAST_NAME", lastName);
                        homeIntent.putExtra("PHONE", phone);
                        homeIntent.putExtra("USERNAME", username);
                        homeIntent.putExtra("USER_ID", id);

                        LogInActivity.this.startActivity(homeIntent);

                        System.out.println(json);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    System.out.println("THE EMAIL OF THIS USER IS  " + userInfo.getEmail());


                } else {


                    wrongPassword.setVisibility(View.VISIBLE);


                }


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


            }
        });
    }

    public String getJSON(String username, String password){
        String url = "http://ksustudyroom.azurewebsites.net/api/users/login?Username=" + username + "&Password=" + password;

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                                loginInfo = response.toString();
                        System.out.println("THIS IS A THING THAT SHOULD MAKE SOMETHING " + response.toString());
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyLog.d(TAG, "Error: " + error.getMessage());
                //pDialog.hide();
            }
        }) ;

        System.out.println("LOGIN INFO IS NOT SUPPOSE TO BE NULL HERE" + loginInfo);

        AppController.getInstance().addToRequestQueue(jsonObjReq);


        return loginInfo;
    }

    public void threadWait(){

    }
}
