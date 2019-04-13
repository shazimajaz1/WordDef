package com.practice.projects;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
    This app uses the Webster Dictionary API to search for Definition.
    The terms of service from the website requires the developer to not
    monetize the app in any way.
 */


/*
    This activity is mainly the loading activity. Some of the connection setup
    work is done in this activity and the progress is displayed through a progress
    bar. The app does not load until the connection has been setup properly
 */
public class MainActivity extends AppCompatActivity {
    /*
        Field Variables

     */


    /*
        Class Constants
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up the network connection with the host.
        //Stay on this screen until the network is all setup
        //then go to the query search screen.
        //If the network does not work, simply go the screen that
        //shows the user an error message and tells the user steps
        //to fix the issue.

        //Check the connection


        //Start the activity
        Intent intent = new Intent(this, SearchScreenActivity.class);
        startActivity(intent);

    }
}
