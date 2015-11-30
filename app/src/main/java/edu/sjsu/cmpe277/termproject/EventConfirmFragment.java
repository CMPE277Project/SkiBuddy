package edu.sjsu.cmpe277.termproject;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventConfirmFragment extends Fragment {



    private TextView confirm_tv;
    private TextView eventname_tv;
    private TextView eventdescription_tv;
    private TextView starttime_tv;
    private TextView endtime_tv;



    public EventConfirmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_event_confirm, container, false);

        Bundle bundle = getArguments();
        String confirmstring = bundle.getString("SUCCESS_KEY");
        String event_name = bundle.getString("EVENT_NAME");
        String event_description = bundle.getString("EVENT_DESCRIPTION");
        String start_time = bundle.getString("EVENT_START");
        String end_time = bundle.getString("EVENT_END");
        // Inflate the layout for this fragment


        event_name = "Event Name: "+ event_name;
        event_description = "Description: " +event_description;
        start_time = "Start from " + start_time;
        end_time = "End at " + end_time;


        confirm_tv = (TextView)rootView.findViewById(R.id.confirmMessage);
        confirm_tv.setText(confirmstring);

        eventname_tv = (TextView)rootView.findViewById(R.id.eventname);
        eventname_tv.setText(event_name);

        eventdescription_tv = (TextView)rootView.findViewById(R.id.eventdescription);
        eventdescription_tv.setText(event_description);

        starttime_tv = (TextView)rootView.findViewById(R.id.starttime);
        starttime_tv.setText(start_time);

        endtime_tv = (TextView)rootView.findViewById(R.id.endtime);
        endtime_tv.setText(end_time);


        return rootView;
    }




}
