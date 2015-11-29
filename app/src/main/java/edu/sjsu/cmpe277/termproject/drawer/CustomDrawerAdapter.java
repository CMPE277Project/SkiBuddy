package edu.sjsu.cmpe277.termproject.drawer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import edu.sjsu.cmpe277.termproject.R;

/**
 * Created by emy on 11/27/15.
 */
public class CustomDrawerAdapter extends ArrayAdapter<ObjectDrawer> {

    private Context context;
    private int resourceId;
    private ObjectDrawer[] objectDrawers;

    public CustomDrawerAdapter(Context context, int resourceId, ObjectDrawer[] objectDrawers) {
        super(context, resourceId, objectDrawers);
        this.context=context;
        this.resourceId = resourceId;
        this.objectDrawers = objectDrawers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View Listview = convertView;

        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();

        Listview = layoutInflater.inflate(resourceId, parent, false);

        ImageView imageView = (ImageView)Listview.findViewById(R.id.imageViewIcon);
        TextView textView =(TextView)Listview.findViewById(R.id.textViewIcon);

        ObjectDrawer objectDrawer = objectDrawers[position];
        textView.setText(objectDrawer.getName());

        return Listview;
//        return super.getView(position, convertView, parent);
    }
}
