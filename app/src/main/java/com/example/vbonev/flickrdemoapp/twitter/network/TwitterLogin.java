package com.example.vbonev.flickrdemoapp.twitter.network;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.codepath.oauth.OAuthLoginActivity;
import com.example.vbonev.flickrdemoapp.R;


public class TwitterLogin  extends OAuthLoginActivity<TwitterClient> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_login);

    }
    @Override
    public void onLoginSuccess() {
        Intent i = new Intent(this, TwitterFeedActivity.class);
        startActivity(i);
    }

    @Override
    public void onLoginFailure(Exception e) {

    }

    public void loginTwitter(View view) {
        getClient().connect();
    }
}
