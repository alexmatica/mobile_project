package mfie1944.ubb.ro.travellerapp.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import mfie1944.ubb.ro.travellerapp.R;
import mfie1944.ubb.ro.travellerapp.model.TravelDestination;

/**
 * Created by Alex on 10/29/2017.
 */

public class CustomAdapter extends ArrayAdapter<TravelDestination> {
    private List<TravelDestination> myDestinations;
    Context myContext;

    private static class ViewHolder {
        TextView nameView;
        TextView descView;
        RatingBar rating;
    }

    public CustomAdapter(List<TravelDestination> myDestinations, Context context){
        super(context, R.layout.row_item, myDestinations);
        this.myDestinations = myDestinations;
        this.myContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        TravelDestination destination = getItem(position);

        ViewHolder viewHolder;
        final View result;

        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.nameView = convertView.findViewById(R.id.destinationName);
            viewHolder.descView = convertView.findViewById(R.id.shortDesc);
            viewHolder.rating = convertView.findViewById(R.id.ratingBar);
            result = convertView;
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.nameView.setText(destination.getName());
        viewHolder.descView.setText(destination.getDescription());
        viewHolder.rating.setRating(destination.getRating()/2);
        return result;
    }

    public void updateDestinations(List<TravelDestination> newDestinations){
        myDestinations.clear();
        myDestinations.addAll(newDestinations);
        this.notifyDataSetChanged();
    }
}
