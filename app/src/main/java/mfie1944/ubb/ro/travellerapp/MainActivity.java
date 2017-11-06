package mfie1944.ubb.ro.travellerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String EXTRA_NAME = "travellerapp.name";
    public static String EXTRA_FEEDBACK = "travellerapp.feedback";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendFeedback(View view){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"alexbv2301@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback from [" + ((EditText)findViewById(R.id.nameEditText)).getText().toString() + "]");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email:\n" + ((EditText)findViewById(R.id.feedbackEditText)).getText().toString());
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(this, "There are no email clients installed!", Toast.LENGTH_SHORT).show();
        }
    }

    public void continueHandle(View view){
        Intent intent = new Intent(this, BestDestinationsActivity.class);
        startActivity(intent);
    }
}
