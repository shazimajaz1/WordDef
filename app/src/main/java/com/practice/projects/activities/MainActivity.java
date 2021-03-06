package com.practice.projects.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.practice.projects.R;
import com.practice.projects.api_setup.NetworkUtility;

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

        //Check the connection using connection manager.
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        final boolean isConnected = networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();

        //Check the connection to the server using Network Utils class
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

               boolean isServerReplying = NetworkUtility.checkConnection();
                //Only start the search activity if the internet is connected.
                Intent searchIntent;
                if (isServerReplying && isConnected) {
                    //Start the activity
                    searchIntent = new Intent(MainActivity.this, SelectionActivity.class);
                    startActivity(searchIntent);

                    //Get rid of this activity
                    finish();
                } else {
                    //Let the user know that there is a problem with the connection
                    TextView noConnectionTextDisplay = findViewById(R.id.no_connection_text_view);
                    noConnectionTextDisplay.setText(getString(R.string.connection_error));
                }
            }
        });
        thread.start();
    }
}
