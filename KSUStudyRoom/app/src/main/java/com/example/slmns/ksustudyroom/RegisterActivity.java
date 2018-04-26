package com.example.slmns.ksustudyroom;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText registerUserName = (EditText) findViewById(R.id.registerUsernameText);
        final EditText registerEmail= (EditText) findViewById(R.id.registerEmailText);
        final EditText registerPassword = (EditText) findViewById(R.id.registerPasswordText);
        final Button registerButton = (Button) findViewById(R.id.registerButton);
        final TextView invalidUsernameLabel = (TextView) findViewById(R.id.invalidUsernameLabel);
        final TextView invalidEmailLabel = (TextView) findViewById(R.id.invalidEmailLabel);
        final TextView invalidPasswordLabel = (TextView) findViewById(R.id.invalidPasswordLabel);

        //Registers or submits the user's data.
        registerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //--begin validation code
                boolean missingField = false;

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

                    if(missingField){
                        throw new NullPointerException();
                    }
                    else{
                        //user is created here
                       // createUser(String.valueOf(registerUserName.getText()), String.valueOf(registerEmail.getText()), String.valueOf(registerPassword.getText()));

                        /**
                         * Switches to the login page after registering.
                         */
                        registerButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent loginIntent = new Intent(RegisterActivity.this, LogInActivity.class);
                                RegisterActivity.this.startActivity(loginIntent);
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

}
