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



    private TextView test_tv;

    public EventConfirmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_event_confirm, container, false);

        Bundle bundle = getArguments();
        String teststring = bundle.getString("SUCCESS_KEY");
        // Inflate the layout for this fragment


        test_tv = (TextView)rootView.findViewById(R.id.testFragment);
        test_tv.setText(teststring);


        return rootView;
    }


}
