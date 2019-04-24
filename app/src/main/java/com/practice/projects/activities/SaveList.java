package com.practice.projects.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.practice.projects.R;
import com.practice.projects.adapters.SaveListAdapter;
import com.practice.projects.database.Definitions;
import com.practice.projects.database.DefinitionsViewModel;

import java.util.List;

/*
    This activity shows the saved history of the users search list.
    It also uses the features of the recycler view that allows the user to remove the
    data from the history as necessary.
 */

public class SaveList extends AppCompatActivity {
    private SaveListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_list);

        //Set the screen background to same as the main screen
        this.getWindow().setBackgroundDrawableResource(R.drawable.selection_screen_background);
        //Setup the toolbar
        Toolbar toolbar = findViewById(R.id.save_list_activity_toolbar);
        toolbar.setTitle("Save History:");
        toolbar.setBackgroundColor(Color.TRANSPARENT);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_navigation_back);
        setSupportActionBar(toolbar);

        //Set the recycler view
        RecyclerView recyclerView = findViewById(R.id.save_list_recycler_view);
        adapter = new SaveListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Retrieve the data from the database.
        //Since the data is wrapped in the live view, it is being updated using
        //an observer as necessary.
        /*
        Class and Field variables
     */
        DefinitionsViewModel dvm = ViewModelProviders.of(this).get(DefinitionsViewModel.class);
        dvm.getAllDefinitions().observe(this, new Observer<List<Definitions>>() {
            @Override
            public void onChanged(@Nullable List<Definitions> definitions) {
                //Update the words
                adapter.setDefinitions(definitions);

            }
        });


    }
}
