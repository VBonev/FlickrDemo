package com.example.vbonev.flickrdemoapp.twitter.network;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.vbonev.flickrdemoapp.R;
import com.example.vbonev.flickrdemoapp.flickr.PagerAdapter;
import com.example.vbonev.flickrdemoapp.flickr.model.FlickrPhoto;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class TwitterFeedActivity extends AppCompatActivity {
    private TwitterClient twitterClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_feed);
        twitterClient = new TwitterClient(this);
        getTweets("Sofia");
    }

    private void getTweets(String query) {
        twitterClient.searchQueryTweets(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  JSONObject json) {
                Log.d("DEBUG", "result: " + json.toString());
                // Add new photos to SQLite
                try {
                    JSONArray tweets = json.getJSONArray("statuses");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        }, query);
    }
}
