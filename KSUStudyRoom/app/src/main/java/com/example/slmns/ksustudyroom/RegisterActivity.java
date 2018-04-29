package com.example.slmns.ksustudyroom;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Handles the register page.
 */
public class RegisterActivity extends AppCompatActivity {
private String userName;
private String email;
private String password;

public String getUserName() { return this.userName; }
public String getEmail(){return this.email;}
public String getPassword(){return this.password;}

//User user = new User();
//LogInActivity signIn = new LogInActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText registerUserName = (EditText) findViewById(R.id.registerUsernameText);
        final EditText registerEmail= (EditText) findViewById(R.id.registerEmailText);
        final EditText registerPassword = (EditText) findViewById(R.id.registerPasswordText);
        final EditText registerFirstName = (EditText) findViewById(R.id.registerFirstNameText);
        final EditText registerLastName = (EditText) findViewById(R.id.registerLastNameText);
        final EditText registerPhone = (EditText) findViewById(R.id.registerPhone);
        final Button registerButton = (Button) findViewById(R.id.registerButton);

        final TextView invalidUsernameLabel = (TextView) findViewById(R.id.invalidUsernameLabel);
        final TextView invalidEmailLabel = (TextView) findViewById(R.id.invalidEmailLabel);
        final TextView invalidPasswordLabel = (TextView) findViewById(R.id.invalidPasswordLabel);
        final TextView invalidFirstNameLabel = (TextView)findViewById(R.id.invalidFirstNameTextLabel);
        final TextView invalidLastNameLabel =  (TextView) findViewById(R.id.invalidLastNameTextLabel);
        final TextView invalidPhoneLabel =  (TextView) findViewById(R.id.invalidPhoneLabel);


        //Registers or submits the user's data.
        registerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //--begin validation code
                boolean missingField = false;
                User userInfo = new User();
                String username;
                String firstName;
                String lastName;
                String password;
                String phone;
                String email;

                try{
                    if(registerUserName.getText().toString().isEmpty()){
                        invalidUsernameLabel.setVisibility(View.VISIBLE);
                        missingField = true;
                    }
                    else{
                        invalidUsernameLabel.setVisibility(View.INVISIBLE);
                    }

                    if(registerEmail.getText().toString().isEmpty()){
                        invalidEmailLabel.setText("Missing email");
                        invalidEmailLabel.setVisibility(View.VISIBLE);

                        missingField = true;
                    }
                   else if(registerEmail.getText().toString().contains("kennesaw.edu") == false){

                        invalidEmailLabel.setText("Invalid email");
                        invalidEmailLabel.setVisibility(View.VISIBLE);

                    }
                    else{
                       invalidEmailLabel.setVisibility(View.INVISIBLE);
                    }

                    if(registerPassword.getText().toString().isEmpty()){
                        invalidPasswordLabel.setVisibility(View.VISIBLE);
                        missingField = true;
                    }
                    else{
                        invalidPasswordLabel.setVisibility(View.INVISIBLE);
                    }
                    if(registerFirstName.getText().toString().isEmpty()){
                        invalidFirstNameLabel.setVisibility(View.VISIBLE);
                        missingField = true;
                    }
                    else{
                        invalidFirstNameLabel.setVisibility(View.INVISIBLE);
                    }
                    if(registerLastName.getText().toString().isEmpty()){
                        invalidLastNameLabel.setVisibility(View.VISIBLE);
                        missingField = true;
                    }
                    else{
                        invalidLastNameLabel.setVisibility(View.INVISIBLE);
                    }
                    if(registerPhone.getText().toString().isEmpty() || registerPhone.getText().toString().length() < 10 || !(registerPhone.getText().toString()).matches("[0-9]+")){
                        invalidPhoneLabel.setVisibility(View.VISIBLE);
                        missingField = true;
                    }
                    else{
                        invalidPhoneLabel.setVisibility(View.INVISIBLE);
                    }

                    if(missingField){
                        throw new NullPointerException();
                    }
                    else{
                        //user is created here
                       // createUser(String.valueOf(registerUserName.getText()), String.valueOf(registerEmail.getText()), String.valueOf(registerPassword.getText()));
                        userInfo.setFirstName(registerFirstName.getText().toString());
                        userInfo.setEmail(registerEmail.getText().toString());
                        userInfo.setLastName(registerLastName.getText().toString());
                        userInfo.setUsername(registerUserName.getText().toString());
                        userInfo.setPassword(registerPassword.getText().toString());
                        userInfo.setPhone(registerPhone.getText().toString());
                        userInfo.setId("");



                        /**
                         * Switches to the login page after registering.
                         *
                         */
                        registerButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {



                            }

                        });
                    }
                }
                catch(NullPointerException e){
                    e.printStackTrace();
                }
                //--end of validation code



            }
        });

    }

    public void createUser(String userName, String email, String password) {


    }

    public class SignUpTask extends AsyncTask<User, Void, Void> {
        private User user;

        SignUpTask(User user){
            this.user = user;
        }


        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;
        User userInfo = new User();

        @Override
        protected Void doInBackground(User... users){
            user = users[0];
            String result = "";
            String inputLine;

            try {
                URL url = new URL("http://ksustudyroom.azurewebsites.net/api/users/signup");
                //URL url = new URL(stringUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();


                //request headers
                conn.setRequestMethod("POST");
                conn.setRequestProperty("User-Agent", "Mozilla/5.0");


                //String urlParameters = "?"+user;

                //send post request
                conn.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(user.toString());
                wr.flush();
                wr.close();

                int responseCode = conn.getResponseCode();
                System.out.println("\nSending 'POST' request to URL : " + url);
                System.out.println("Response Code : " + responseCode);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                //String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                //print in String
                System.out.println(response.toString());
                System.out.println("THE OUT OF THE THING IS"+ result);


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

            return null;
        }

        protected void onPostExecute(Void result){
            super.onPostExecute(result);
        }
    }

}




//Intent loginIntent = new Intent(RegisterActivity.this, LogInActivity.class);
//RegisterActivity.this.startActivity(loginIntent);
                                /*user.setFirstName(registerFirstName.getText().toString());
                                user.setLastName(registerLastName.getText().toString());
                                user.setEmail(registerEmail.getText().toString());
                                user.setPhone(registerPhone.getText().toString());
                                user.setUsername(registerUserName.getText().toString());
                                user.setPassword(registerPassword.getText().toString());*/


                                /*AsyncTask.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        //get all users api call
                                        try {
                                            URL url = new URL("http://ksustudyroom.azurewebsites.net/api/users/signup");
                                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();


                                            //request headers
                                            conn.setRequestMethod("POST");
                                            conn.setRequestProperty("User-Agent", "Mozilla/5.0");


                                            String urlParameters = "?"+user;

                                            //send post request
                                            conn.setDoOutput(true);
                                            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                                            wr.writeBytes(urlParameters);
                                            wr.flush();
                                            wr.close();

                                            int responseCode = conn.getResponseCode();
                                            System.out.println("\nSending 'POST' request to URL : " + url);
                                            System.out.println("Response Code : " + responseCode);

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
                                            //jsonArray.get(1).toString();



                                        } catch (MalformedURLException e) {
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }



                                    }});*/
