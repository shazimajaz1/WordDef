package com.practice.projects.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import java.util.List;

public class DefinitionsRepository {
    /*
        Member variables
     */
    private DatabaseAccessObject definitionsDAO;
    private LiveData<List<Definitions>> definitions;


    /*
        Constructor 1: gets the database in the application context.
     */
    DefinitionsRepository(Application application){
        DefinitionsRoomDatabase db = DefinitionsRoomDatabase.getDatabase(application);
        this.definitionsDAO = db.definitionsDAO();
        this.definitions = definitionsDAO.getAllDefinitions();

    }

    /*
        Getter: Returns the parameter
            It servers as a wrapper for the definitions
        @Parameters: None
     */
    LiveData<List<Definitions>> getAllDefinitions(){
        return definitions;
    }

    /*
        This method servers as a wrapper for insert command
     */
    public void insert(Definitions definitions){
        new InsertAsyncTask(definitionsDAO).execute(definitions);
    }


    /*
        Async Task as a subclass allows the queries to be run on a thread other than
        UI to maintain the fluidity in the UI
     */
    private static class InsertAsyncTask extends AsyncTask<Definitions, Void, Void>{
        /*
            Member variables
         */
        private DatabaseAccessObject databaseAccessObject;

        /*
            Constructor 1: Default
        */
        InsertAsyncTask(DatabaseAccessObject databaseAccessObject){
            this.databaseAccessObject = databaseAccessObject;
        }

        /*
            The work inside this method will be done in background on a thread other than
            UI thread.

         */
        @Override
        protected Void doInBackground(Definitions... definitions) {
            databaseAccessObject.insert(definitions[0]);
            return null;
        }
    }



}
