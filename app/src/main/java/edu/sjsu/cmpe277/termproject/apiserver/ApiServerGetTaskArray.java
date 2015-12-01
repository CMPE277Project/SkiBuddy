package edu.sjsu.cmpe277.termproject.apiserver;

import android.os.AsyncTask;

import org.json.JSONArray;

/**
 * Created by davchen on 11/30/15.
 */
public class ApiServerGetTaskArray extends AsyncTask<String, Void, JSONArray> {

    /*
     * input: params must be 1 string
     *        params[0] = endpoint
     */
    @Override
    protected JSONArray doInBackground(String... params) {
        ApiServer apiServer = new ApiServer();
        JSONArray response = apiServer.getRequestArray(params[0]);
        return response;
    }
}