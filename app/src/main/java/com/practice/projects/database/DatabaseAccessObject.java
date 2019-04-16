package com.practice.projects.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/*
    This interface also known as DAO serves as the object to access the data
    from the database. All the queries that app needs must be specified in this interface.
    This interface is also the part of the lower level implementation of the database.
    SQL library is used in this case. The room allows the methods to be defined
    to be used as queries rather than typing the queries itself.

    This interface can be modified to add further functionality by specifying more queries as needed.

    @Requirement 1: It must be executed on a separate thread. It is not allowed to be used
        directly on the main thread.
    @Requirement 2: It requires an entity object to be present. In this case, the entity object is
        Definitions class.
 */
@Dao
public interface DatabaseAccessObject {

    /*
        Setter 1: This method inserts a queryString and the associated definition
        into the database enclosed as a Definition object.
     */
    @Insert
    void insert(Definitions definitions);

    /*
        Setter 2: This method deletes all the data from the data base
        Once called, this method will delete all the data. A sequence of check
        must be implemented on higher level to ensure that user does not end up deleting all
        the data by mistake.
     */
    @Query("DELETE FROM definitions_table")
    void deleteAll();

    /*
        Getter 1: This method returns all definitions stored on the database
        The order in which the data is returned is the order in which it was stored.
        Uses live data from lifecycle library which ensures that all the observers are updated
        through the use of observer data pattern of any changes that are made to the data.
     */
    @Query("SELECT * FROM definitions_table")
    LiveData<List<Definitions>> getAllDefinitions();






}
