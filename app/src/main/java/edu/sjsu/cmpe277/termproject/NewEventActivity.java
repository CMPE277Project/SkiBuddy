package edu.sjsu.cmpe277.termproject;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.sjsu.cmpe277.termproject.models.Event;

public class NewEventActivity extends AppCompatActivity {

    private EditText EditTextName;
    private EditText EditTextDescription;
    private EditText EditTextDate;
    private EditText EditTextStartTime;
    private EditText EditTextEndTime;

    private TextView test;

    private String eventName;
    private String eventDescription;
    private String startTime;
    private String endTime;
    private String eventDate;

    private SimpleDateFormat df_date = new SimpleDateFormat("MM-dd-yyyy");
    private SimpleDateFormat df_time = new SimpleDateFormat("hh:mm");
    private static final String TAG= "MyActivity";

    private Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        //fortest
        submitButton = (Button)findViewById(R.id.button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date myDate, start_time, end_time;

                //get date and start end time
                try {
                    EditTextName = (EditText) findViewById(R.id.input_new_event_name);
                    eventName = EditTextName.getText().toString();
                    EditTextDescription = (EditText) findViewById(R.id.input_description);
                    eventDescription = EditTextDescription.getText().toString();

                    //SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

                    EditTextDate = (EditText) findViewById(R.id.editText_event_date);
                    EditTextStartTime = (EditText) findViewById(R.id.editText_event_begin_time);
                    EditTextEndTime = (EditText) findViewById(R.id.editText_event_end_time);


                    Log.v(TAG, "Event name here:");
                    Log.v(TAG, EditTextDescription.getText().toString());

                    myDate = df_date.parse(EditTextDate.getText().toString());
                    eventDate = myDate.getDate() + "-" + (myDate.getMonth()+1 ) + "-" + (1900 + myDate.getYear());

                    start_time = df_time.parse(EditTextStartTime.getText().toString());
                    startTime =  start_time.getHours() + ":" +start_time.getMinutes();

                    end_time = df_time.parse(EditTextEndTime.getText().toString());
                    endTime =  end_time.getHours() + ":" +end_time.getMinutes();


                    String st= eventDate + " " +endTime;
                    Log.v(TAG, st);
                    //test = (TextView)findViewById(R.id.testtextView);
                    //test.setText(st);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //new an Event
                Event newEvent = new Event();
                newEvent.setTitle(eventName);
                newEvent.setDescription(eventDescription);
                newEvent.setStartTime(startTime);
                newEvent.setEndTime(endTime);

                //need to store the event here






                //add fragment
                Fragment newFragment = createCustomFragment(eventName, eventDescription, startTime,endTime);
                FragmentTransaction ft = getFragmentManager().beginTransaction().add(R.id.addfragment, newFragment);
                ft.commit();

            }

        });


    }

    private Fragment createCustomFragment(String event_name, String event_description, String start_time, String end_time ) {
        Bundle bundle = new Bundle();
        bundle.putString("SUCCESS_KEY", "Event created!");
        bundle.putString("EVENT_NAME", event_name);
        bundle.putString("EVENT_DESCRIPTION", event_description);
        bundle.putString("EVENT_START", start_time);
        bundle.putString("EVENT_END", end_time);
        Fragment f = new EventConfirmFragment();
        f.setArguments(bundle);

        Log.v(TAG, "CreateCustomeFragment");
        Log.v(TAG, event_name);
        Log.v(TAG, event_description);
        Log.v(TAG, start_time);

        return f;

    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_event, menu);
        return true;
    }
    */

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


