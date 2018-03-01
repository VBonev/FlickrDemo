package com.example.vbonev.flickrdemoapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.codepath.oauth.OAuthLoginActivity;
import com.example.vbonev.flickrdemoapp.R;
import com.example.vbonev.flickrdemoapp.network.FlickrClient;

public class FlickrLoginActivity extends OAuthLoginActivity<FlickrClient>{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getClient().connect();
            }
        });

    }
    @Override
    public void onLoginSuccess() {
        Intent i = new Intent(this, FlickrGalleryActivity.class);
        startActivity(i);
    }

    @Override
    public void onLoginFailure(Exception e) {

    }
}
