package edu.sjsu.cmpe277.termproject.models;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import edu.sjsu.cmpe277.termproject.R;
import edu.sjsu.cmpe277.termproject.apiserver.ApiServerGetTask;
import edu.sjsu.cmpe277.termproject.apiserver.ApiServerGetTaskArray;

public class ListViewActivity extends AppCompatActivity {
    private static final String baseURL = "http://79.170.44.117/rajat-bansal.com/skiBuddy/";
    private ListView listView_;
    private Intent selectedEvent;

    private ArrayList<String> eventsList_ = new ArrayList<String>();
    private ArrayAdapter<String> lobbyAdapter_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        this.listView_ = (ListView)findViewById(R.id.listview);

        this.listView_.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String eventId = (String)((TextView) view).getText();
                try{
                    eventDetail(eventId);
                } catch (Exception e){
                    System.out.println(e.toString());
                }
            }
        });

        populateEvents();
    }

    private void eventDetail(String eventId) {
        selectedEvent = new Intent(ListViewActivity.this, MembersListActivity.class);
        selectedEvent.putExtra("eventId", eventId);
        startActivity(selectedEvent);
    }

    private void populateEvents() {

//        String JSONString = "{\"status\":\"success\",\"data\":{\"events\":[\"Beginners Cup\",\"Intermediates Cup\",\"Advanced Cup\"]}}";
        ApiServerGetTask apiServerGetTask = new ApiServerGetTask();
        ApiServerGetTaskArray apiServerGetTaskArray = new ApiServerGetTaskArray();
        String endpoint = baseURL + "getAllEvents.php";

        try {

//            JSONObject jsonObj = apiServerGetTask.execute(endpoint).get();
            JSONArray jsonArray = apiServerGetTaskArray.execute(endpoint).get();
//            System.out.println(jsonObj.toString());
            System.out.println("JSONArray String is: " + jsonArray.toString());
//            JSONArray jEvents = jsonObj.getJSONObject("data").getJSONArray("events");
            JSONObject json = jsonArray.getJSONObject(0);
            System.out.println("JSON TITLE: "+ json.toString());
//            JSONObject jEvents = jsonObj.getJSONObject("title");
            ArrayList<String> events = new ArrayList<String>();
            for(int i = 0; i < jsonArray.length(); i++){
                events.add((String) jsonArray.getJSONObject(i).get("title"));
                System.out.println("event contents are: " + events.get(i));
            }
            System.out.println("event contents are: " + events);
//            for(int i = 0; i < jEvents.length(); i++){
//                events.add(jEvents.getString(i));
//            }

            this.eventsList_ = events;
            this.lobbyAdapter_ = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.eventsList_);
            this.listView_.setAdapter(this.lobbyAdapter_);
        }  catch (InterruptedException e) {
            System.out.println(e.toString());
        } catch (ExecutionException e) {
            System.out.println(e.toString());
        } catch (JSONException e) {
            System.out.println(e.toString());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_view, menu);
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
