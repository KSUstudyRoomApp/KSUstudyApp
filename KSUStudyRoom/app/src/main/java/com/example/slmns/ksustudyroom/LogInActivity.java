package com.example.slmns.ksustudyroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import org.w3c.dom.Text;


/**
 * Handles the login page.
 */
public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        AWSMobileClient.getInstance().initialize(this).execute();


        final EditText loginUsername = (EditText) findViewById(R.id.loginUsernameText);
        final EditText loginPassword = (EditText) findViewById(R.id.loginPasswordText);
        final Button loginButton = (Button) findViewById(R.id.loginButton);
        final TextView registerLink = (TextView) findViewById(R.id.loginRegisterTextView);
        final TextView homePageLink = (TextView) findViewById(R.id.loginToHomeLink);
        final TextView invalidLoginUsernNameLabel = (TextView) findViewById(R.id.invalidLoginUserNameLabel);
        final TextView invalidLoginPasswordLabel = (TextView) findViewById(R.id.invalidLoginPasswordLabel);
        final TextView toStudyGroupLink = (TextView) findViewById(R.id.toStudyGroupLink);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean missingField = false;
              try
              {
                  if(loginUsername.getText().toString().isEmpty()){
                      invalidLoginUsernNameLabel.setVisibility(View.VISIBLE);
                      missingField = true;
                  }
                  else{
                      invalidLoginUsernNameLabel.setVisibility(View.INVISIBLE);
                  }

                  if(loginPassword.getText().toString().isEmpty()){
                      invalidLoginPasswordLabel.setVisibility(View.VISIBLE);
                      missingField = true;
                  }
                  else{
                      invalidLoginPasswordLabel.setVisibility(View.INVISIBLE);
                  }

                  if(missingField){
                      throw new Exception();
                  }
                  else{
                      Intent homeIntent = new Intent(LogInActivity.this, HomeActivity.class);
                      LogInActivity.this.startActivity(homeIntent);
                  }
              }
              catch (Exception e){
                  e.printStackTrace();
              }

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

        toStudyGroupLink.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent studyGroupIntent = new Intent(LogInActivity.this,StartStudyGroupActivity.class );
                LogInActivity.this.startActivity(studyGroupIntent);
            }
        });

    }
}
