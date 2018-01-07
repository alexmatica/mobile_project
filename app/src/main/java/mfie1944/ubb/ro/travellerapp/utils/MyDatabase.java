package mfie1944.ubb.ro.travellerapp.utils;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by fmatica on 1/7/18.
 */

public class MyDatabase {
    private static FirebaseDatabase database;

    public static FirebaseDatabase getDatabase(){
        if (database == null){
            database = FirebaseDatabase.getInstance();
            database.setPersistenceEnabled(true);
        }
        return database;
    }
}
