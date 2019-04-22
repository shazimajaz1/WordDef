package com.practice.projects.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Definitions.class}, version = 1, exportSchema = false)
public abstract class DefinitionsRoomDatabase extends RoomDatabase {

    /*
        This object builds a layer of interface on top of the getter methods
        in the database.
     */
    public abstract DatabaseAccessObject definitionsDAO();
    private static DefinitionsRoomDatabase  INSTANCE; //to ensure that there is no double instance

    /*
        This method acts as a constructor and only creates a new database if there
        is no instance of it already created.
        If there is an instance already created it returns that instance.
     */
    public static DefinitionsRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (DefinitionsRoomDatabase.class){
                if(INSTANCE == null ){
                    //Create a new database
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DefinitionsRoomDatabase.class,
                            "definitions_database")
                            /*
                                This can be time consuming as it destroys the database and
                                then re creates it making it a slow process
                                -It needs to be improved later but is currently serving as a premature
                                implementation.
                             */
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;

    }

}
