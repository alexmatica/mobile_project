package mfie1944.ubb.ro.travellerapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import mfie1944.ubb.ro.travellerapp.model.TravelDestination;
import mfie1944.ubb.ro.travellerapp.repository.TravelDestinationsRepository;
import mfie1944.ubb.ro.travellerapp.utils.DestinationAdapter;
import mfie1944.ubb.ro.travellerapp.utils.MyDatabase;


public class BestDestinationsActivity extends AppCompatActivity implements DestinationAdapter.OnItemClickListener, DestinationAdapter.OnItemLongClickListener {

    public static final String KEY_CREATE = "ro.ubb.mfie1944.CREATE";
    public static final String KEY_UPDATE = "ro.ubb.mfie1944.UPDATE";
    public static final String D_ID = "ro.ubb.mfie1944.destination_id";
    public static final String D_NAME = "ro.ubb.mfie1944.destination_name";
    public static final String D_DESC = "ro.ubb.mfie1944.destination_desc";
    public static final String D_RATING = "ro.ubb.mfie1944.destination_rating";
    public static final String D_PHOTO = "ro.ubb.mfie1944.destination_photo";


    public static TravelDestinationsRepository repository;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mRecyclerAdapter;
    RecyclerView.LayoutManager mRecyclerManager;
    DatabaseReference mDatabase;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_destinations);

        mDatabase = MyDatabase.getDatabase().getReference("traveller");
        mDatabase.keepSynced(true);

        repository = new TravelDestinationsRepository();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TravelDestinationsRepository temp = dataSnapshot.getValue(TravelDestinationsRepository.class);
                if (temp != null)
                    repository.setDestinations(temp.getDestinations());
                mRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerAdapter = new DestinationAdapter(repository, this, this);
        mRecyclerManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mRecyclerManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK){
                mRecyclerAdapter.notifyDataSetChanged();
                mDatabase.setValue(repository);
            }
        }
    }

    public void onClickAdd(View view){
        if (MainActivity.getFirebaseAuthInstance().getCurrentUser().isAnonymous()){
            Toast.makeText(this, "You cannot add items in guest mode!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, EditDestinationActivity.class);
        intent.putExtra("CU", KEY_CREATE);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onItemClick(int id) {
        if ( !MainActivity.getFirebaseAuthInstance().getCurrentUser().isAnonymous() &&
                repository.getDestination(id).getUniqueEmail().equals(
                        MainActivity.getFirebaseAuthInstance().getCurrentUser().getEmail())) {
            Intent intent = new Intent(this, EditDestinationActivity.class);
            intent.putExtra("CU", KEY_UPDATE);
            intent.putExtra(D_ID, id);
            intent.putExtra(D_NAME, repository.getDestination(id).getName());
            intent.putExtra(D_DESC, repository.getDestination(id).getDescription());
            intent.putExtra(D_RATING, repository.getDestination(id).getRating());
            intent.putExtra(D_PHOTO, repository.getDestination(id).getPhotoLink());
            startActivityForResult(intent, 1);
        }
        else{
            Toast.makeText(this, "You cannot edit this item!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemLongClick(int id) {

        if (MainActivity.getFirebaseAuthInstance().getCurrentUser().isAnonymous()){
            Toast.makeText(this, "You cannot remove items in guest mode!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!MainActivity.getFirebaseAuthInstance().getCurrentUser().getEmail().equals(
                repository.getDestination(id).getUniqueEmail()
        )){
            Toast.makeText(this, "You cannot remove this item!", Toast.LENGTH_SHORT).show();
            return;
        }

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        repository.deleteDestination(id);
                        mRecyclerAdapter.notifyDataSetChanged();
                        mDatabase.setValue(repository);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                        break;
                }
            }
        };
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setMessage("Are you sure to delete this destination?").
                setPositiveButton("Yes", dialogClickListener).
                setNegativeButton("No", dialogClickListener).show();
    }
}