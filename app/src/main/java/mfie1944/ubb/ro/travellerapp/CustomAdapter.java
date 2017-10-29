package mfie1944.ubb.ro.travellerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alex on 10/29/2017.
 */

public class CustomAdapter extends ArrayAdapter<Destination> {
    private ArrayList<Destination> myDestinations;
    Context myContext;

    private static class ViewHolder {
        TextView nameView;
        TextView descView;
        ImageView emojiRating;
    }

    public CustomAdapter(ArrayList<Destination> myDestinations, Context context){
        super(context, R.layout.row_item, myDestinations);
        this.myDestinations = myDestinations;
        this.myContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Destination destination = getItem(position);

        ViewHolder viewHolder;
        final View result;

        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.nameView = convertView.findViewById(R.id.destinationName);
            viewHolder.descView = convertView.findViewById(R.id.shortDesc);
            viewHolder.emojiRating = convertView.findViewById(R.id.emojiRating);
            result = convertView;
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.nameView.setText(destination.getName());
        viewHolder.descView.setText(destination.getshortDescription());
        viewHolder.emojiRating.setImageResource(R.mipmap.ic_launcher);
        return result;
    }
}
