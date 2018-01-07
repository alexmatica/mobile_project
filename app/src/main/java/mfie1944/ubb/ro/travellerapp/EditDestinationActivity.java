package mfie1944.ubb.ro.travellerapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import mfie1944.ubb.ro.travellerapp.model.TravelDestination;

public class EditDestinationActivity extends AppCompatActivity {

    int posInList;
    String CU;
    int editId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_destination);

        EditText nameEditText = findViewById(R.id.nameEditText);
        EditText descEditText = findViewById(R.id.descEdit);
        RatingBar ratingBar = findViewById(R.id.destRating);

        Intent intent = getIntent();

        CU = intent.getStringExtra("CU");
        if (CU.equals(BestDestinationsActivity.KEY_UPDATE)) {
            editId = intent.getIntExtra(BestDestinationsActivity.D_ID, 0);
            String destName = intent.getStringExtra(BestDestinationsActivity.D_NAME);
            String destDesc = intent.getStringExtra(BestDestinationsActivity.D_DESC);
            int destRating = intent.getIntExtra(BestDestinationsActivity.D_RATING,0);
            String destPhoto = intent.getStringExtra(BestDestinationsActivity.D_PHOTO);

            nameEditText.setText(destName);
            descEditText.setText(destDesc);
            ratingBar.setRating(destRating/2);
        } else if (CU.equals(BestDestinationsActivity.KEY_CREATE)){
            nameEditText.setText("");
            descEditText.setText("");
            ratingBar.setRating(0);
        }

        initChart(nameEditText.getText().toString());
    }

    private void initChart(String strName){
        PieChart pieChart = findViewById(R.id.pieChart);
        List<PieEntry> entries = new ArrayList<>();

        float consonants = 0.0f, vowels = 0.0f;
        for (int i=0; i < strName.length(); i++){
            if ("aeiou".contains(strName.substring(i, i+1)))
                vowels ++;
            else
                consonants ++;
        }
        if (consonants > 0 || vowels > 0) {
            float consPercent = (consonants / (float)strName.length()) * 100.0f;
            float vowPercent = 100.0f - consPercent;

            Toast.makeText(this, " " + consPercent + " " + vowPercent + " ", Toast.LENGTH_LONG).show();

            entries.add(new PieEntry(consPercent, "Consonants"));
            entries.add(new PieEntry(vowPercent, "Vowels"));

            PieDataSet pieDataSet = new PieDataSet(entries, "");
            pieDataSet.setColors(Color.rgb(255, 0, 0), Color.rgb(0, 255, 0));
            PieData pieData = new PieData(pieDataSet);
            pieChart.setData(pieData);
            pieChart.invalidate();
        }
    }

    @SuppressLint("StaticFieldLeak")
    public void onClickSave(View view){

        EditText nameEditText = findViewById(R.id.nameEditText);
        EditText descEditText = findViewById(R.id.descEdit);
        RatingBar ratingBar = findViewById(R.id.destRating);

        if (nameEditText.getText().length() > 0 && descEditText.getText().length() > 0){
            TravelDestination td = new TravelDestination();
            td.setName(nameEditText.getText().toString());
            td.setDescription(descEditText.getText().toString());
            td.setRating((int) (ratingBar.getRating() * 2));
            td.setPhotoLink("");
            td.setUniqueEmail(MainActivity.getFirebaseAuthInstance().getCurrentUser().getEmail());

            if (CU.equals(BestDestinationsActivity.KEY_CREATE))
                BestDestinationsActivity.repository.addDestination(td);
            else
                BestDestinationsActivity.repository.updateDestination(editId, td);

            Intent done = new Intent();
            setResult(RESULT_OK, done);
            finish();
        } else{
            nameEditText.setError("Required");
            descEditText.setError("Required");
        }
    }

}
