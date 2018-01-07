package mfie1944.ubb.ro.travellerapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by fmatica on 12/2/17.
 */

public class TravelDestination implements Serializable{

    private int id;

    private String name;

    private String description;

    private int rating;

    private String photoLink;

    // one account per email is enabled so the email of an user is unique
    private String uniqueEmail;

    public TravelDestination() {
    }

    public TravelDestination(String name, String description, int rating, String photoLink) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.photoLink = photoLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getUniqueEmail() {
        return uniqueEmail;
    }

    public void setUniqueEmail(String uniqueEmail) {
        this.uniqueEmail = uniqueEmail;
    }
}
