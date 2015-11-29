package edu.sjsu.cmpe277.termproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewEventActivity extends AppCompatActivity {

    private EditText EditTextName;
    private EditText EditTextDescription;
    private EditText EditTextDate;

    private String eventName;
    private String eventDescription;
    private String startTime;
    private String endTime;

    private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        EditTextName = (EditText) findViewById(R.id.input_new_event_name);
        eventName = EditTextName.getText().toString();
        EditTextDescription = (EditText) findViewById(R.id.input_description);
        eventDescription = EditTextName.getText().toString();

        //SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        EditTextDate = (EditText) findViewById(R.id.editText_event_date);
        try {
            Date myDate = df.parse(EditTextDate.getText().toString());
            String myText = myDate.getDate() + "-" + (myDate.getMonth() + 1) + "-" + (1900 + myDate.getYear());
            //Log.i(TAG, myText);
        } catch (ParseException e) {
            e.printStackTrace();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
