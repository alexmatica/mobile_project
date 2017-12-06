package mfie1944.ubb.ro.travellerapp;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import mfie1944.ubb.ro.travellerapp.model.RemoveDialogFragment;
import mfie1944.ubb.ro.travellerapp.model.TravelDestination;
import mfie1944.ubb.ro.travellerapp.utils.CustomAdapter;


public class BestDestinationsActivity extends AppCompatActivity {

    public static final String KEY_CREATE = "ro.ubb.mfie1944.CREATE";
    public static final String KEY_UPDATE = "ro.ubb.mfie1944.UPDATE";
    public static final String D_ID = "ro.ubb.mfie1944.destination_id";
    public static final String D_NAME = "ro.ubb.mfie1944.destination_name";
    public static final String D_DESC = "ro.ubb.mfie1944.destination_desc";
    public static final String D_RATING = "ro.ubb.mfie1944.destination_rating";
    public static final String D_PHOTO = "ro.ubb.mfie1944.destination_photo";

    ListView listView;

    private CustomAdapter customAdapter;
    private List<TravelDestination> myDestinations = new ArrayList<>();
    private AppDatabase appDatabase;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_destinations);

        listView = findViewById(R.id.destinationsListView);

        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase = AppDatabase.getInstance(getApplicationContext());
                myDestinations = appDatabase.travelDestinationDao().getAll();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid){
                customAdapter = new CustomAdapter(myDestinations, getApplicationContext());
                listView.setAdapter(customAdapter);
                listView.setOnItemClickListener((parent, view, position, id) -> {
                    TravelDestination destination = myDestinations.get(position);

                    Intent intent = new Intent(BestDestinationsActivity.this, EditDestinationActivity.class);
                    intent.putExtra("CU", KEY_UPDATE);
                    intent.putExtra(D_ID, destination.getId());
                    intent.putExtra(D_NAME, destination.getName());
                    intent.putExtra(D_DESC, destination.getDescription());
                    intent.putExtra(D_RATING, destination.getRating());
                    intent.putExtra(D_PHOTO, destination.getPhotoLink());
                    intent.putExtra("posInList", position);
                    startActivityForResult(intent, 1);
                });

                listView.setOnItemLongClickListener((parent, view, position, id) ->  {
                    RemoveDialogFragment removeFragment = new RemoveDialogFragment();
                    removeFragment.setItemPos(position);
                    removeFragment.show(getFragmentManager(), "remove");
                    return true;
                });

            }
        }.execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK){
                reloadList();
            }
        }
    }

    public void onClickAdd(View view){
        Intent intent = new Intent(this, EditDestinationActivity.class);
        intent.putExtra("CU", KEY_CREATE);
        startActivityForResult(intent, 1);
    }

    @SuppressLint("StaticFieldLeak")
    public void onClickRemove(int itemPos){
        TravelDestination travelDestination = myDestinations.get(itemPos);

        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase = AppDatabase.getInstance(getApplicationContext());
                appDatabase.travelDestinationDao().delete(travelDestination);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid){
                reloadList();
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private void reloadList(){
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase = AppDatabase.getInstance(getApplicationContext());
                myDestinations = appDatabase.travelDestinationDao().getAll();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid){
                customAdapter.updateDestinations(myDestinations);
            }
        }.execute();
    }
}
