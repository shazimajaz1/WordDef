package com.practice.projects;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import org.w3c.dom.Text;

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

        //Setup the toolbar
        Toolbar toolbar = findViewById(R.id.resultScreenToolbar);
        toolbar.setTitle("Search Results");
        toolbar.setBackgroundColor(Color.DKGRAY);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);


        //Get the user intent
        Intent intent = getIntent();
        String shortDef = intent.getStringExtra(SearchScreenActivity.RESULT_IN_EXTRAS);
        String queryString = intent.getStringExtra(SearchScreenActivity.QUERY_IN_EXTRAS);


        //Display it in the text views
        TextView titleTextView = findViewById(R.id.result_title);
        titleTextView.append(" ");
        titleTextView.append(queryString);
        TextView shortDefText = findViewById(R.id.result_text_view);
        shortDefText.setText(shortDef);

    }
}
