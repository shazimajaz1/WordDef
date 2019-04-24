package com.practice.projects;

import android.arch.lifecycle.ViewModelProviders;
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

import com.practice.projects.database.Definitions;
import com.practice.projects.database.DefinitionsViewModel;
import com.practice.projects.save_list.SaveList;
import com.practice.projects.settings.SettingsActivity;

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

    /*
        Class variables

     */
    private String shortDef;
    private String queryString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_result);

        //Change the background to same as first screen
        this.getWindow().setBackgroundDrawableResource(R.drawable.selection_screen_background);
        //Setup the toolbar
        Toolbar toolbar = findViewById(R.id.resultScreenToolbar);
        toolbar.setTitle("Search Results");
        toolbar.setBackgroundColor(Color.TRANSPARENT);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_navigation_back);
        setSupportActionBar(toolbar);


        //Get the user intent
        Intent intent = getIntent();
        shortDef = intent.getStringExtra(SearchScreenActivity.RESULT_IN_EXTRAS);
        queryString = intent.getStringExtra(SearchScreenActivity.QUERY_IN_EXTRAS);


        //Display it in the text views
        TextView titleTextView = findViewById(R.id.result_title);
        titleTextView.append(" ");
        titleTextView.append(queryString);
        TextView shortDefText = findViewById(R.id.result_text_view);
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
         This is a premature implementation where the app crashes if the values already exist in the
         list.
     */
    public void saveToFile(View view) {
        //Insert the word into the Database
        try {
            //Insert the word and definition into the database
            DefinitionsViewModel dvm = ViewModelProviders.of(this).get(DefinitionsViewModel.class);
            dvm.insert(new Definitions(queryString, shortDef));

            //Let the user know the operation was successful
            Toast.makeText(this, getString(R.string.saveSuccessMessage), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            //Let the user know that the operation failed.
            Toast.makeText(this, getString(R.string.saveFailedMessage), Toast.LENGTH_SHORT);


        }

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
