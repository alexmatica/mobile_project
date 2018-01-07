package mfie1944.ubb.ro.travellerapp.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RatingBar;
import android.widget.TextView;

import mfie1944.ubb.ro.travellerapp.R;
import mfie1944.ubb.ro.travellerapp.repository.TravelDestinationsRepository;

/**
 * Created by fmatica on 1/6/18.
 */

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.DestinationViewHolder>{

    TravelDestinationsRepository repository;
    OnItemClickListener onItemClickListener;
    OnItemLongClickListener onItemLongClickListener;
    Context context;

    public DestinationAdapter(TravelDestinationsRepository repository,
                              OnItemClickListener onItemClickListener,
                              OnItemLongClickListener onItemLongClickListener) {
        this.repository = repository;
        this.onItemClickListener = onItemClickListener;
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public DestinationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        DestinationViewHolder destinationViewHolder = new DestinationViewHolder(view);
        context = parent.getContext();
        return destinationViewHolder;
    }

    @Override
    public void onBindViewHolder(DestinationViewHolder holder, int position) {
        holder.nameView.setText(repository.getDestinations().get(position).getName());
        holder.descView.setText(repository.getDestinations().get(position).getDescription());
        holder.rating.setRating(repository.getDestinations().get(position).getRating() / 2);
    }

    @Override
    public int getItemCount() {
        return repository.getDestinations().size();
    }

    public interface OnItemClickListener {
        void onItemClick(int id);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(final int id);
    }

    public class DestinationViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener, View.OnLongClickListener{

        TextView nameView;
        TextView descView;
        RatingBar rating;

        public DestinationViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            nameView = itemView.findViewById(R.id.destinationName);
            descView = itemView.findViewById(R.id.shortDesc);
            rating = itemView.findViewById(R.id.ratingBar);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            onItemLongClickListener.onItemLongClick(getAdapterPosition());
            return true;
        }
    }
}
