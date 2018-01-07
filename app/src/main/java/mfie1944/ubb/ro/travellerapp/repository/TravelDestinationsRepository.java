package mfie1944.ubb.ro.travellerapp.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mfie1944.ubb.ro.travellerapp.model.TravelDestination;

/**
 * Created by fmatica on 1/6/18.
 */

public class TravelDestinationsRepository implements Serializable {

    private List<TravelDestination> destinations;

    public TravelDestinationsRepository() {
        this.destinations = new ArrayList<>();
    }

    public void addDestination(TravelDestination travelDestination){
        travelDestination.setId(generateId());
        destinations.add(travelDestination);
    }

    public void deleteDestination(int id){
        destinations.remove(id);

        for (int i=0; i<destinations.size(); i++)
            destinations.get(i).setId(i+1);
    }

    public void updateDestination(int id, TravelDestination newDestination){
        destinations.get(id).setDescription(newDestination.getDescription());
        destinations.get(id).setName(newDestination.getName());
        destinations.get(id).setRating(newDestination.getRating());
        destinations.get(id).setPhotoLink(newDestination.getPhotoLink());
    }

    public TravelDestination getDestination(int id){
        return destinations.get(id);
    }

    public List<TravelDestination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<TravelDestination> destinations) {
        this.destinations = destinations;
    }

    private int generateId(){
        return  destinations.size() + 1;
    }
}
