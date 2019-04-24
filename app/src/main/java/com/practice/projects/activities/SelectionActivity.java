package com.practice.projects.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.practice.projects.R;
import com.practice.projects.settings.SettingsActivity;

/*
    This activity proves a menu for the user to choose the navigation to various
    parts of the application.
 */

public class SelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        //Set the new Background
        this.getWindow().setBackgroundDrawable(getDrawable(R.drawable.selection_screen_background));
        //Setup the toolbar
        Toolbar toolbar = findViewById(R.id.selection_screen_toolbar);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setBackgroundColor(Color.TRANSPARENT);
        setSupportActionBar(toolbar);
    }

    /*
        This method is executed when the activity starts to inflate the menu
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.settings_menu, menu);

        //return the menu from super
        return super.onCreateOptionsMenu(menu);
    }

    /*
        This method adds the action that is performed when the menu button is selected
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Start the settings activity
        startActivity(new Intent(this, SettingsActivity.class));

        return super.onOptionsItemSelected(item);
    }

    /*
                This method is invoked when Search Screen Button is clicked
             */
    public void openSearchScreen(View view) {
        //Start the search screen activity
        Intent intent = new Intent(this, SearchScreenActivity.class);
        startActivity(intent);
    }

    /*
        This method is invoked when Save List Button is Clicked
     */
    public void openSaveList(View view) {
        //Start the save list activity
        Intent intent = new Intent(this, SaveList.class);
        startActivity(intent);
    }



}
