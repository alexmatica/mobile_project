package mfie1944.ubb.ro.travellerapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import mfie1944.ubb.ro.travellerapp.dao.TravelDestinationDao;
import mfie1944.ubb.ro.travellerapp.model.TravelDestination;

/**
 * Created by fmatica on 12/2/17.
 */

@Database(entities = {TravelDestination.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase{

   public abstract TravelDestinationDao travelDestinationDao();

   private static AppDatabase appDatabase;

   public static synchronized AppDatabase getInstance(Context context){
        if (appDatabase == null){
            appDatabase = Room.databaseBuilder(context,
                    AppDatabase.class, "traveller-db").
                    fallbackToDestructiveMigration().build();
            appDatabase.preload();
        }
        return appDatabase;
   }

   private void preload(){
       if (travelDestinationDao().count() == 0){
           TravelDestination[] dummyDestinations = {
                new TravelDestination("Miami", "Sunny, beach, chicks!", 10, "miami.png"),
                new TravelDestination("Singapore", "Crowded, diverse, beautiful", 9, "singap.png"),
                new TravelDestination("London", "Super crowded, rainy, cold", 7, "london.png")
           };
           for(TravelDestination td: dummyDestinations) {
               beginTransaction();
               try {
                   travelDestinationDao().insertOne(td);
                   setTransactionSuccessful();
               } finally {
                   endTransaction();
               }
           }
       }
   }
}
