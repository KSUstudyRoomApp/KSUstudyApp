package com.example.slmns.ksustudyroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import static com.example.slmns.ksustudyroom.R.layout.activity_home;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    private TextView mGestureText;
    private GestureDetector mGestureDetector;
    final TextView homePageLink = (TextView) findViewById(R.id.loginToHomeLink);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final TextView homePageLink = (TextView) findViewById(R.id.loginToHomeLink);
        super.onCreate(savedInstanceState);
        setContentView(activity_home);

        mGestureText = (TextView) findViewById(R.id.gestureStatus);

        // Create an object of our Custom Gesture Detector Class
        CustomGestureDetector customGestureDetector = new CustomGestureDetector();
        // Create a GestureDetector
        mGestureDetector = new GestureDetector(this, customGestureDetector);
        // Attach listeners that'll be called for double-tap and related gestures
        mGestureDetector.setOnDoubleTapListener(customGestureDetector);
    }

    public void confirmButton (View v) {
        setContentView(activity_home);
        //This is the part that'll send emails and stuff. I don't know that part all that well yet.
    }

    public void editButton (View v) {
        //setContentView(BOOKINGROOMPAGE);
        //There's probably more elegant ways to do this with, say, fragments.
    }

    public void cancelButton (View v) {
        setContentView(activity_home);
        //This one just sends you right back to the start.
    }
}

class CustomGestureDetector implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {
    private static final String TAG = "MyActivity";
    private TextView mGestureText;
    private GestureDetector mGestureDetector;

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        mGestureText.setText("onSingleTapConfirmed");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        mGestureText.setText("onDoubleTap");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        mGestureText.setText("onDoubleTapEvent");
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        mGestureText.setText("onDown");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        mGestureText.setText("onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        mGestureText.setText("onSingleTapUp");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        mGestureText.setText("onScroll");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        mGestureText.setText("onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        mGestureText.setText("onFling " + e1.getX() + " - " + e2.getX());

        if (e1.getX() < e2.getX()) {
            //This is where we'll set the view to be that of the Chat page.
            //setContentView(R.layout.CHATPAGE);
            Log.d(TAG, "Left to Right swipe performed");
        }

        if (e1.getX() > e2.getX()) {
            //This is where we'll set the view to be that of the Calendar page.
            //setContentView(R.layout.CALENDARPAGE);
            Log.d(TAG, "Right to Left swipe performed");
        }

        if (e1.getY() < e2.getY()) {
            //This is where we'll set the view to be that of the Reserve Rooms page.
            //setContentView(R.layout.activity_choose_campus);
            Log.d(TAG, "Up to Down swipe performed");

           // Intent regiserIntent = new Intent(LogInActivity.this, HomeActivity.class);
           // LogInActivity.this.startActivity(regiserIntent);

        }

        return true;
    }
}

