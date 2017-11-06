package mfie1944.ubb.ro.travellerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import mfie1944.ubb.ro.travellerapp.model.Destination;
import mfie1944.ubb.ro.travellerapp.utils.CustomAdapter;


public class BestDestinationsActivity extends AppCompatActivity {

    ArrayList<Destination> myDestinations;
    ListView listView;
    private static CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_destinations);

        listView = (ListView) findViewById(R.id.destinationsListView);
        myDestinations = new ArrayList<>();
        myDestinations.add(new Destination("Miami", "SoAlive", 5, 5));
        myDestinations.add(new Destination("LA", "SoAlive", 5, 5));
        myDestinations.add(new Destination("Cluj", "SoAlive", 5, 5));
        myDestinations.add(new Destination("Singapore", "SoAlive", 5, 5));
        myDestinations.add(new Destination("Whatever", "SoAlive", 5, 5));
        myDestinations.add(new Destination("Dorohoi", "SoAlive", 5, 5));
        myDestinations.add(new Destination("Brasov", "SoAlive", 5, 5));
        myDestinations.add(new Destination("Iasi", "SoAlive", 5, 5));
        myDestinations.add(new Destination("Londra", "SoAlive", 5, 5));
        myDestinations.add(new Destination("Nush", "SoAlive", 5, 5));
        myDestinations.add(new Destination("Ubb", "SoAlive", 5, 5));
        myDestinations.add(new Destination("Poli", "SoAlive", 5, 5));
        myDestinations.add(new Destination("Ana", "SoAlive", 5, 5));
        myDestinations.add(new Destination("Are", "SoAlive", 5, 5));
        myDestinations.add(new Destination("Mere", "SoAlive", 5, 5));
        myDestinations.add(new Destination("Destule", "SoAlive", 5, 5));

        customAdapter = new CustomAdapter(myDestinations, getApplicationContext());
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Destination destination = myDestinations.get(position);

                Intent intent = new Intent(BestDestinationsActivity.this, EditDestinationActivity.class);
                intent.putExtra("destinationName", destination.getName());
                intent.putExtra("destinationDesc", destination.getshortDescription());
                intent.putExtra("posInList", position);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK){
                int pos = data.getIntExtra("posInList", 0);
                String newDesc = data.getStringExtra("newDesc");
                myDestinations.get(pos).setshortDescription(newDesc);
                customAdapter.notifyDataSetChanged();
            }
        }
    }
}
