package com.example.pk.openvoidjobs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by PK on 04/12/2015.
 */
public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        String username = getIntent().getStringExtra("Username");

        TextView tv = (TextView)findViewById(R.id.TVusername);
        tv.setText(username);

        DownloadWebpageExample example = new DownloadWebpageExample();

        //String webpage = example.toString();

        //TextView webpageTV = (TextView) findViewById(R.id.restJSONget);
        //webpageTV.setText(webpage);

    }

}
