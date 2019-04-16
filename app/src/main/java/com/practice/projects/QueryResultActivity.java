package com.practice.projects;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.practice.projects.save_list.SaveList;

/*
    The result of the query is displayed in this activity.
    It also allows the user to change the text size of the result display.
    It also allows the user to copy the text in the search box.
    Along with the actual result, similar results are also shown at the botton
    of the screen.
 */
public class QueryResultActivity extends AppCompatActivity {

    /*
        Class Constants
     */
    public static final String CLIP_TEXT_KEY = QueryResultActivity.class.getSimpleName() + "_CLIP_TEXT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_result);

        //Setup the toolbar
        Toolbar toolbar = findViewById(R.id.resultScreenToolbar);
        toolbar.setTitle("Search Results");
        toolbar.setBackgroundColor(Color.DKGRAY);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_navigation_back);
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
        shortDefText.setBackgroundColor(Color.LTGRAY);
        shortDefText.setTextColor(Color.BLACK);
        shortDefText.setText(shortDef);

    }

    /*
        This method inflates the menu from the drawable menu directory.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //Inflate the menu
        getMenuInflater().inflate(R.menu.button_menu, menu);

        //Call the super method to do the rest
        return super.onCreateOptionsMenu(menu);
    }

    /*
        This method implements on click listeners on the Menus
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Implement action listener on the menu buttons
        int itemId = item.getItemId();

        //Choose the appropriate action menu based on the button selected
        switch (itemId) {
            case R.id.action_open_save_list:
                openSaveList();
                break;
            case R.id.action_settings:
                openAppSettings();
        }
        return super.onOptionsItemSelected(item);
    }

    /*
        This method is invoked when save list button in the toolbar is touched.
     */
    private void openSaveList() {
        //Open the saveList Activity
        Intent saveListActivityIntent = new Intent(this, SaveList.class);
        startActivity(saveListActivityIntent);
    }

    /*
        This method is invoked when teh settings button in the toolbar is touched.
     */
    private void openAppSettings() {
        //Open the Settings Activity
        Intent settingsActivityIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsActivityIntent);
    }

    /*
         This method is invoked when the user wants to save the result to a file
     */
    public void saveToFile(View view) {

    }

    /*
        This method is invoked when the user wants to copy the text to clipboard.
     */
    public void copyToClipboard(View view) {
        //Get the text from the text view
        TextView resultTextView = findViewById(R.id.result_text_view);
        String resultText = resultTextView.getText().toString();

        //Copy the Text to the clipboard
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData dataClip = ClipData.newPlainText(CLIP_TEXT_KEY, resultText);
        clipboardManager.setPrimaryClip(dataClip);

        //Tell the user that the text has been copied to the clipboard
        Toast.makeText(this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();


    }
}
