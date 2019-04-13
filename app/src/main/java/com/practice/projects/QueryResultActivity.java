package com.practice.projects;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
    The result of the query is displayed in this activity.
    It also allows the user to change the text size of the result display.
    It also allows the user to copy the text in the search box.
    Along with the actual result, similar results are also shown at the botton
    of the screen.
 */
public class QueryResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_result);

    }
}
