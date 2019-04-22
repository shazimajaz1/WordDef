package com.practice.projects;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/*
    This activity proves a menu for the user to choose the navigation to various
    parts of the application.
 */

public class SelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
    }
}
