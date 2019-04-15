package com.practice.projects.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import android.support.annotation.NonNull;

/*
    This server as an entity inside the database schema.
    On Lower level implementation, this entity will be added
    each time an insert method is called.
    On higher level implementation, the data must first be stored in this entity before it can
    be saved in the database.
 */
@Entity(tableName = "definitions_table")
public class Definitions {
    /*
        Class variables: This is the place holder where the data can be stored or extracted
     */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "query_string")
    private String queryString; //Cannot be null
    @ColumnInfo(name = "definitions_string")
    private String definitionsString;

    /*
        Constructor 1: Default
        @param: queryString
        @param: definitionsString
     */
    public Definitions(String queryString, String definitionsString) {
        this.queryString = queryString;
        this.definitionsString = definitionsString;
    }

    /*
        Getter 1:
        @parameters: None
        @return: queryString
     */
    @NonNull
    public String getQueryString() {
        return this.queryString;
    }

    /*
        Getter 2:
        @parameters: None
        @return: definitionsString
     */
    public String getDefinitionsString() {
        return this.definitionsString;
    }
}
