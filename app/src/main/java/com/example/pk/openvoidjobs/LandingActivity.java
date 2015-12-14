package com.example.pk.openvoidjobs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
//import com.example.pk.openvoidjobs.DownloadWebpageTask.AsyncResponse;

/**
 * Created by PK on 04/12/2015.
 */


public class LandingActivity extends AppCompatActivity /*implements AsyncResponse*/{

    public static String webpageExample = "";
    @Bind(R.id.link_viewjobs) TextView _viewjobsLink;

    String webpageURL = "http://52.19.81.247:8080/api/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        ButterKnife.bind(this);

        String username = getIntent().getStringExtra("Username");

        TextView tv = (TextView)findViewById(R.id.TVusername);
        tv.setText(username);

        _viewjobsLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Start ViewJobsMapActivity activity
                Intent intent = new Intent(getApplicationContext(), ViewJobsMapActivity.class);
                startActivity(intent);
            }

        });



        DownloadWebpageTask example = new DownloadWebpageTask();

        //execute the async task
        example.execute(webpageURL);

        // http://stackoverflow.com/questions/9963691/android-asynctask-sending-callbacks-to-ui

        String webpage = "";
        webpage = webpageExample;
        Log.v("Webpage", webpage);

        TextView webpageTV = (TextView) findViewById(R.id.restJSONget);
        webpageTV.setText(webpage);
    }

}

