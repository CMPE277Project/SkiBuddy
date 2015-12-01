package edu.sjsu.cmpe277.termproject.apiserver;

import android.os.AsyncTask;

import org.json.JSONObject;

/**
 * Created by davchen on 11/30/15.
 */
public class ApiServerPostTask extends AsyncTask<String, Void, JSONObject> {

    /*
     * input: params must be 2 strings
     *        params[0] = endpoint
     *        params[1] = parameters in JSON formatted string
     */
    @Override
    protected JSONObject doInBackground(String... params) {
        ApiServer apiServer = new ApiServer();
        JSONObject response = apiServer.postRequest(params[0], params[1]);
        return response;
    }

}
