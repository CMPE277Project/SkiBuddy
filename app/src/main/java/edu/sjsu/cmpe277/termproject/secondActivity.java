package edu.sjsu.cmpe277.termproject;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;

import edu.sjsu.cmpe277.termproject.Fragments.CreateFragments;
import edu.sjsu.cmpe277.termproject.drawer.CustomDrawerAdapter;
import edu.sjsu.cmpe277.termproject.drawer.ObjectDrawer;

public class secondActivity extends AppCompatActivity {

    private TextView textView, textView2;
    private ProfilePictureView profilePictureView;
    private Intent intent;
    private Toolbar toolbar;
    private ListView listView;
    private DrawerLayout drawerLayout;
    private String[] nav_drawerList;
    private ObjectDrawer[] objectDrawers = new ObjectDrawer[3];
    private CustomDrawerAdapter customDrawerAdapter;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private CharSequence charSequence, title;

    //for test
    private Button initEventButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        title = charSequence = getTitle();

       intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String name = intent.getStringExtra("lastName");
        String userId = intent.getStringExtra("Id");


//Test to see if receiving data from fb.
        profilePictureView = (ProfilePictureView)findViewById(R.id.profilePic);
        profilePictureView.setProfileId(userId);
       // textView = (TextView)findViewById(R.id.displayName);
        textView2 =(TextView)findViewById(R.id.textName);
        textView2.setText(firstName + " " + name);
        //textView.setText(name);
        toolbar = (Toolbar)findViewById(R.id.toolBar);

        //toolbar.setTitle("Welcome");

        toolbar.setLogo(R.drawable.ic_menu_image);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        listView = (ListView)findViewById(R.id.left_drawer);
        nav_drawerList = getResources().getStringArray(R.array.drawer_items);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);



        objectDrawers[0] = new ObjectDrawer(R.drawable.ic_open_event, "Create Event");
        objectDrawers[1] = new ObjectDrawer(R.drawable.ic_create_event, "Invitations");
       // objectDrawers[2] = new ObjectDrawer(R.drawable.ic)
        objectDrawers[2] = new ObjectDrawer(R.drawable.ic_logout, "Logout");

        customDrawerAdapter = new CustomDrawerAdapter(this, R.layout.listviews_items, objectDrawers);

        listView.setAdapter(customDrawerAdapter);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(title);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(charSequence);
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        listView.setOnItemClickListener(new DrawerListener());


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

	//for test
	 initEventButton = (Button)findViewById(R.id.newEventsButton);
        initEventButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                intent = new Intent(secondActivity.this, NewEventActivity.class);
                startActivity(intent);
            }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
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

        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class DrawerListener implements ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }
    private void selectItem(int position) {

        Fragment fragment = null;
        switch(position) {
            case 0:
                fragment = new CreateFragments();
                break;
           /* case 1:
                fragment = new InviteFragment();
                break;
            case 2:
                fragment = new LogOutFragment();
                break;*/

            default:
                break;
        }

        if(fragment!=null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.contentFrame, fragment).commit();

            listView.setItemChecked(position, true);
            listView.setSelection(position);
            getSupportActionBar().setTitle(nav_drawerList[position]);
            drawerLayout.closeDrawer(listView);
        }
        else{
            Log.e("MainActivity", "Error in creating fragment");
        }
    }
}
