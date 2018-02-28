package com.example.vbonev.flickrdemoapp.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.codepath.oauth.OAuthLoginActivity;
import com.example.vbonev.flickrdemoapp.R;
import com.example.vbonev.flickrdemoapp.network.FlickrClient;

public class LoginActivity extends OAuthLoginActivity<FlickrClient>{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flickr_login);

    }
    @Override
    public void onLoginSuccess() {
        Intent i = new Intent(this, FlickrGallery.class);
        startActivity(i);
    }

    @Override
    public void onLoginFailure(Exception e) {

    }

    public void login(View view) {
        getClient().connect();
    }
}
