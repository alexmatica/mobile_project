package mfie1944.ubb.ro.travellerapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import mfie1944.ubb.ro.travellerapp.model.TravelDestination;

/**
 * Created by fmatica on 12/2/17.
 */

@Dao
public interface TravelDestinationDao {

    @Query("SELECT * FROM traveldestination")
    List<TravelDestination> getAll();

    @Query("SELECT * FROM traveldestination where rating = :user_rating")
    List<TravelDestination> getAllByRating(int user_rating);

    @Query("SELECT * FROM traveldestination where name = :user_name")
    List<TravelDestination> getByName(String user_name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(TravelDestination... travelDestinations);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOne(TravelDestination travelDestination);

    @Delete
    void delete (TravelDestination travelDestination);

    @Update
    void update(TravelDestination travelDestination);

    @Query("SELECT Count(*) FROM traveldestination")
    int count();


}
