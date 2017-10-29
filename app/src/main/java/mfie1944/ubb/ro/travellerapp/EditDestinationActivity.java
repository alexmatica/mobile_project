package mfie1944.ubb.ro.travellerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EditDestinationActivity extends AppCompatActivity {

    int posInList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_destination);

        Intent intent = getIntent();
        String destName = intent.getStringExtra("destinationName");
        String destDesc = intent.getStringExtra("destinationDesc");
        this.posInList = intent.getIntExtra("posInList", 0);

        TextView textView = (TextView) findViewById(R.id.nameTextView);
        textView.setText(destName);

        EditText editText = (EditText) findViewById(R.id.descEdit);
        editText.setText(destDesc);
    }

    public void onClick(View view){
        Intent intent = new Intent();
        EditText editText = (EditText) findViewById(R.id.descEdit);
        intent.putExtra("newDesc", editText.getText().toString());
        intent.putExtra("posInList", posInList);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        onClick(findViewById(R.id.button));
    }
}
