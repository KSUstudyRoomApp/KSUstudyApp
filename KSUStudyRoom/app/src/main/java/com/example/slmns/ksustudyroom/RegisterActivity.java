package com.example.slmns.ksustudyroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.models.nosql.UserAccountsDO;
import com.amazonaws.mobile.auth.core.IdentityManager;




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

DynamoDBMapper dynamoDBMapper;
IdentityManager identityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText registerUserName = (EditText) findViewById(R.id.registerUsernameText);
        final EditText registerEmail= (EditText) findViewById(R.id.registerEmailText);
        final EditText registerPassword = (EditText) findViewById(R.id.registerPasswordText);
        final Button registerButton = (Button) findViewById(R.id.registerButton);

        //Registers or submits the user's data.
        registerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                //createUser(String.valueOf(registerUserName.getText()), String.valueOf(registerEmail.getText()), String.valueOf(registerPassword.getText()));
                createUser("1","testName", "test@test.com", "testword");
                //name =String.valueOf(registerUserName.getText());
               // email = String.valueOf(registerEmail.getText());
                //password = String.valueOf(registerPassword.getText());
            }
        });

        //begin_test code feel free to delete.
        final TextView registerToTestLink = (TextView) findViewById(R.id.registerToTestLink);


        registerToTestLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regiserIntent = new Intent(RegisterActivity.this, TestActivity.class);
                RegisterActivity.this.startActivity(regiserIntent);
            }
        });
        //end_test code feel free to delete.

    }

    public void createUser(String Id, String userName, String email, String password) {

        final UserAccountsDO userItem = new UserAccountsDO();

        userItem.setUserId(Id);

        userItem.setUserName(userName);
        userItem.setEmail(email);
        userItem.setPassword(password);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(userItem);
                // Item saved
            }
        }).start();
    }

}
