package com.example.slmns.ksustudyroom;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.models.nosql.UserAccountsDO;
import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.mobile.auth.ui.SignInUI;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.AWSStartupHandler;
import com.amazonaws.mobile.client.AWSStartupResult;



/**
 * Handles the login page.
 */
public class LogInActivity extends AppCompatActivity {

    DynamoDBMapper dynamoDBMapper;
    String userName = "";
    String Password = "";
    UserAccountsDO userItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        AWSMobileClient.getInstance().initialize(this).execute();

        final EditText loginUsername = (EditText) findViewById(R.id.loginUsernameText);
        final EditText loginPassword = (EditText) findViewById(R.id.loginPasswordText);
        final Button loginButton = (Button) findViewById(R.id.loginButton);
        final TextView registerLink = (TextView) findViewById(R.id.loginRegisterTextView);


        /**
         * Switches to the register page on click.
         */
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regiserIntent = new Intent(LogInActivity.this, RegisterActivity.class);
                LogInActivity.this.startActivity(regiserIntent);
            }
        });

    }
    /**
    public void LogInQuery() {

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    } */
}
