package mfie1944.ubb.ro.travellerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void connectHandle(View view){
        Intent intent = new Intent(this, BestDestinationsActivity.class);
        intent.putExtra("email", ((TextView)findViewById(R.id.emailEditText)).getText().toString());
        intent.putExtra("password", ((TextView)findViewById(R.id.passwordEditText)).getText().toString());
        startActivity(intent);
    }
}
