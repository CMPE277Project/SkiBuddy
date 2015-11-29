package edu.sjsu.cmpe277.termproject.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.sjsu.cmpe277.termproject.R;

/**
 * Created by emy on 11/27/15.
 */
public class CreateFragments extends Fragment {
   // @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.create_fragment, container, false);

        return rootView;
    }
}
