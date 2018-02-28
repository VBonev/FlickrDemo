package com.example.vbonev.flickrdemoapp.twitter.network;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.vbonev.flickrdemoapp.R;
import com.example.vbonev.flickrdemoapp.flickr.PagerAdapter;
import com.example.vbonev.flickrdemoapp.flickr.model.FlickrPhoto;
import com.example.vbonev.flickrdemoapp.twitter.network.model.Status;
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
        getTweets("Sofia");
    }

    private void getTweets(String query) {
        twitterClient.searchQueryTweets(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  JSONObject json) {
                Log.d("DEBUG", "result: " + json.toString());
                try {
                    JSONArray tweets = json.getJSONArray("statuses");
                    List<Status> statuses = new ArrayList();
                    for (int i = 0; i < tweets.length(); i++) {
                        statuses.add(new Status(tweets.getJSONObject(i)));
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
}
