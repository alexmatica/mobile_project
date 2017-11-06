package mfie1944.ubb.ro.travellerapp.model;

/**
 * Created by Alex on 10/29/2017.
 */

public class Destination {

    private String name;
    private String shortDescription;
    private int emojiRating;
    private int starRating;

    public Destination(String name, String shortDescription, int emojiRating, int starRating) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.emojiRating = emojiRating;
        this.starRating = starRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getshortDescription() {
        return shortDescription;
    }

    public void setshortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getEmojiRating() {
        return emojiRating;
    }

    public void setEmojiRating(int emojiRating) {
        this.emojiRating = emojiRating;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }
}
