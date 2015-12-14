/*
references:
http://syntx.io/how-to-send-an-http-request-from-android-using-httpurlconnection/
 */


package com.example.pk.openvoidjobs;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadWebpageTask extends AsyncTask<String, Void, JSONArray>{
    public final String LOG_TAG = DownloadWebpageTask.class.getSimpleName();

    public AsyncResponse delegate = null;

    @Override
    protected JSONArray doInBackground(String... params) {
        URL url;
        HttpURLConnection urlConnection = null;

        JSONArray response = new JSONArray();
        String responseString = new String();
        try {
            url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            int responseCode = urlConnection.getResponseCode();


            if(responseCode == HttpURLConnection.HTTP_OK){
                responseString = readStream(urlConnection.getInputStream());
                response = new JSONArray(responseString);

            }else{
                Log.v(LOG_TAG, "Response code:" + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(urlConnection != null)
                urlConnection.disconnect();
        }

        return response;
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

    @Override
    protected void onPostExecute(JSONArray result){
        Log.v("In onPostExecute", result.toString());
        // TODO: find a way to pass 'result' to the LandingActivity
    }
}