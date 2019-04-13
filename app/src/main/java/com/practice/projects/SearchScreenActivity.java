package com.practice.projects;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

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
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

        //Setup the view
        queryStringEditText = findViewById(R.id.searchBar);

        //Set up the toolbar
        toolbar = findViewById(R.id.searchScreenToolbar);
        toolbar.setBackgroundColor(Color.DKGRAY);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Search your word!");
        setSupportActionBar(toolbar);

        //Setup the recycler view with recycler view adapter


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

     */
    public String getWordsResultsThroughNetwork(String queryString){
        //Get the resulted string
        String result = NetworkUtility.getWordResults(queryString);
        try{

            JSONArray jsonArray = new JSONArray(result); //parsing the result through json.

            //Loop through the json array to get the short definition of the query
            int index = 0;
            String shortDef =null;

            while(index < jsonArray.length() && shortDef == null){
                JSONObject defObject = jsonArray.getJSONObject(index);

                //Try to get the short def from the current items
               try{
                    shortDef = defObject.getString("shortdef");
               }catch (Exception e) {
                   e.printStackTrace();
               }
               //Move to the next item in the array
                index++;
            }

            //At this point update the UI with appropriate message
            TextView textView = findViewById(R.id.text_view_search_screen);

            //Format the resulting string
            shortDef = formatString(shortDef);
            textView.setText(shortDef);



        } catch (Exception e){
            e.printStackTrace();
        }

        //fix later
        return null;
    }

    /*
        This method formats the provided string
        This is a premature implementation of the string
        formatter needed in this case. It must be improved before
        the app is published.
     */
    private String formatString(String shortDef){

        //Get rid of side braces
        shortDef = shortDef.substring(2, shortDef.length() - 2);


        return shortDef;
    }
}
