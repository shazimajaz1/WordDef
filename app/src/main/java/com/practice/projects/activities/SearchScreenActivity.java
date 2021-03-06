package com.practice.projects.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.practice.projects.R;
import com.practice.projects.api_setup.NetworkUtility;

import org.json.JSONArray;
import org.json.JSONObject;
//import org.json.simple.parser.JSONParser;

public class SearchScreenActivity extends AppCompatActivity {
    /*
        Member variables
     */
    private EditText queryStringEditText;
    private Toolbar toolbar;
    /*
        Member constants
     */
    public static final String RESULT_IN_EXTRAS = "short_def_extra_string";
    public static final String QUERY_IN_EXTRAS = "query_extra_string";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

        //Set the background with image
        this.getWindow().setBackgroundDrawableResource(R.drawable.selection_screen_background);
        //Setup the view
        queryStringEditText = findViewById(R.id.searchBar);

        //Set up the toolbar
        toolbar = findViewById(R.id.searchScreenToolbar);
        toolbar.setBackgroundColor(Color.TRANSPARENT);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Search your word!");
        toolbar.setNavigationIcon(R.drawable.ic_navigation_back);
        setSupportActionBar(toolbar);


    }

    /*
        This method is invoked when the search button is pressed.
        The search task is done in a separate thread rather than UI thread to avoid
        graphical interruption.
     */
    public void searchQuery(View view) {
        //Retrieve the query string from the search bar
        final String queryString = queryStringEditText.getText().toString();


        //Perform the action to search the text on the internet
        Thread searchThread = new Thread(new Runnable() {
            @Override
            public void run() {
                getWordsResultsThroughNetwork(queryString);
            }
        });
        searchThread.start();


    }

    /*
        This method uses NetworkUtility class to find the result for the
        query string. It can be further modified to get more information about the
        QUERY string.
     */
    public void getWordsResultsThroughNetwork(String queryString) {
        //Get the resulted string
        String result = NetworkUtility.getWordResults(queryString);
        try {

            JSONArray jsonArray = new JSONArray(result); //parsing the result through json.

            //Loop through the json array to get the short definition of the query
            int index = 0;
            String shortDef = null;

            while (index < jsonArray.length() && shortDef == null) {
                JSONObject defObject = jsonArray.getJSONObject(index);

                //Try to get the short def from the current items
                try {
                    shortDef = defObject.getString("shortdef");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //Move to the next item in the array
                index++;
            }



           /* //At this point update the UI with appropriate message
            TextView textView = findViewById(R.id.text_view_search_screen);*/

            //Format the resulting string
            shortDef = formatString(shortDef);

            /*textView.setText(shortDef);*/

            //pass the data to the query result activity.
            Intent resultIntent = new Intent(this, QueryResultActivity.class);
            resultIntent.putExtra(RESULT_IN_EXTRAS, shortDef);
            resultIntent.putExtra(QUERY_IN_EXTRAS, queryString);
            startActivity(resultIntent);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
        This method formats the provided string
        This is a premature implementation of the string
        formatter needed in this case. It must be improved before
        the app is published.
     */

    @org.jetbrains.annotations.NotNull
    private String formatString(String shortDef) {

        //Get rid of side braces
        shortDef = shortDef.substring(2, shortDef.length() - 1);

        //If there are more than one definitions, put them on their
        //own lines
        StringBuilder builder = new StringBuilder();
        int size = shortDef.length();
        int outterIndex = 0;
        while (outterIndex < shortDef.length()) {
            int index = outterIndex;
            while (shortDef.charAt(index) != '"') {
                index++;
            }
            char lowerToUpper = Character.toUpperCase(shortDef.charAt(outterIndex));
            builder.append(lowerToUpper);
            builder.append(shortDef.substring(outterIndex + 1, index));
            builder.append("\n");
            outterIndex = index + 1;
        }
        return builder.toString();
    }
}
