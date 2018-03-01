package com.example.vbonev.flickrdemoapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.vbonev.flickrdemoapp.R;
import com.example.vbonev.flickrdemoapp.adapters.TweetAdapter;
import com.example.vbonev.flickrdemoapp.model.TweetModel;
import com.example.vbonev.flickrdemoapp.network.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class TwitterFeedActivity extends AppCompatActivity {
    private TwitterClient twitterClient;
    private RecyclerView tweetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_feed);
        twitterClient = new TwitterClient(this);
        tweetList = findViewById(R.id.tweet_list);
        tweetList.setHasFixedSize(true);
        tweetList.setLayoutManager(new LinearLayoutManager(this));
        getTweets("Grigor Dimitrov");
    }

    private void getTweets(String query) {
        twitterClient.searchQueryTweets(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  JSONObject json) {
                Log.d("DEBUG", "result: " + json.toString());
                try {
                    JSONArray tweets = json.getJSONArray("statuses");
                    List<TweetModel> statuses = new ArrayList();
                    for (int i = 0; i < tweets.length(); i++) {
                        statuses.add(new TweetModel(tweets.getJSONObject(i)));
                    }
                    tweetList.setAdapter(new TweetAdapter(statuses));
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

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, LandingActivity.class);
        startActivity(i);
    }
}
