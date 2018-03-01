package com.example.vbonev.flickrdemoapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.vbonev.flickrdemoapp.R;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
    }


    public void flickrFlow(View view) {
        Intent i = new Intent(this, FlickrLoginActivity.class);
        startActivity(i);
    }


    public void twitterFlow(View view) {
        Intent i = new Intent(this, TwitterLogin.class);
        startActivity(i);
    }

}
