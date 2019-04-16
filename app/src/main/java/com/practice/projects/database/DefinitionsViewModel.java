package com.practice.projects.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/*
    This class deals with the lower level databases either on the network or on the device
    connected through the repository. The purpose of this class is to ensure that our implementation
    survives the configuration changes that can happen during the lifecycle of an activity.
 */
public class DefinitionsViewModel extends AndroidViewModel {
    /*
        Member variables
     */
    private DefinitionsRepository repository;
    private LiveData<List<Definitions>> allDefinitions;

    public DefinitionsViewModel(@NonNull Application application) {
        super(application);

        //Get all the definitions stored in the database
        repository = new DefinitionsRepository(application);
        allDefinitions = repository.getAllDefinitions();


    }

    /*
        Getter 1: returns all the words. This adds another layer on top of the low level
        implementation and hides the details from the client class that tries to access the data from
        the repository
     */
    LiveData<List<Definitions>> getAllDefinitions() {
        return allDefinitions;
    }

    /*
        Setter 1: Inserts the given definition into the database
        This adds the layer on top the repository.
        @parameter: Definition
     */
    public void insert(Definitions definitions){
        repository.insert(definitions);
    }
}
