package edu.sjsu.cmpe277.termproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;


import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Arrays;

import edu.sjsu.cmpe277.termproject.models.User;

public class MainActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private User user;
    private Intent intent;

 //fortest
    private Button testButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initialize facebook sdk
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        FaceBookSetup();


	        //fortest
        testButton = (Button)findViewById(R.id.testbutton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, NewEventActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void FaceBookSetup() {
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton)findViewById(R.id.login_button);

        loginButton.setReadPermissions(Arrays.asList("public_profile"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
           @Override
           public void onSuccess(LoginResult loginResult) {

               GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                   @Override
                   public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                       try {
                            user = new User();
                            user.setFirstName(jsonObject.getString("first_name"));
                            user.setLastName(jsonObject.getString("last_name"));
                            user.setUserId(jsonObject.getString("id"));
//                            user.setImageFile(jsonObject.getString("picture"));
                           intent = new Intent(MainActivity.this, secondActivity.class);
               intent.putExtra("firstName", user.getFirstName());
               intent.putExtra("lastName", user.getLastName());
               intent.putExtra("Id", user.getUserId());
               startActivity(intent);
                            Log.d("VISUAL: ", user.getLastName());

                        }
                        catch(JSONException ex) {
                            ex.printStackTrace();
                        }
                    }

                });
//               intent = new Intent(MainActivity.this, secondActivity.class);
//               intent.putExtra("firstName", user.getFirstName());
//               intent.putExtra("lastName", user.getLastName());
//               startActivity(intent);
                Bundle parameters = new Bundle();
                parameters.putString("fields", "first_name,last_name");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
           }

           @Override
           public void onCancel() {

           }

           @Override
           public void onError(FacebookException e) {

           }
       });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
