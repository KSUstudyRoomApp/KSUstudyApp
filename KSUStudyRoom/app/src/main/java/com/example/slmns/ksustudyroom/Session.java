package com.example.slmns.ksustudyroom;
import android.content.Context;

import android.content.SharedPreferences;

import android.preference.PreferenceManager;public class Session {

    public static final String PreferenceName = "preferencedata";

    private  SharedPreferences userPreferences;    public  Session(Context cntx){

        userPreferences = PreferenceManager.getDefaultSharedPreferences( cntx);

    }   /* public Session() {    }*/    public  void  setUserName(String userName){

        userPreferences.edit().putString("username", userName).commit();

    }    public String GetUserName() {

        String username = userPreferences.getString("username","");

        return username;

    }    public  void  setId(String id){

        userPreferences.edit().putString("id", id).commit();

    }    public String GetUserId() {

        String id = userPreferences.getString("id","");

        return id;

    }    public  void  setFirstName(String fName){

        userPreferences.edit().putString("firstName", fName).commit();

    }    public String GetFirstName() {

        String firstname = userPreferences.getString("firstname","");

        return firstname;

    }    public  void  setRoomId(String roomId){

        userPreferences.edit().putString("roomid", roomId).commit();

    }    public String GetRoomId() {

        String roomid = userPreferences.getString("roomid","");

        return roomid;

    }    public  void  setTimeSlotId(String timeSlotId){

        userPreferences.edit().putString("timeslotid", timeSlotId).commit();

    }    public String GetTimeSlotId() {

        String timeslotid = userPreferences.getString("timeslotid","");

        return timeslotid;

    }    public  void  setCampusName(String campusName){

        userPreferences.edit().putString("campusname", campusName).commit();

    }    public String GetCampusName() {

        String campusname = userPreferences.getString("campusname","");

        return campusname;

    }
}