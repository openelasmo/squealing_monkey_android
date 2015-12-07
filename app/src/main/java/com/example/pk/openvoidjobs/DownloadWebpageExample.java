package com.example.pk.openvoidjobs;

import android.content.AbstractThreadedSyncAdapter;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

public class DownloadWebpageExample extends AbstractThreadedSyncAdapter{

    @Override
            public void onPerformSync(Account account,  ) {}

    URL url = new URL("http://www.android.com/");
    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
    try {
        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        readStream(in);
        finally {
            urlConnection.disconnect();
        }
    }


}