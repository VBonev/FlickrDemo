package com.example.vbonev.flickrdemoapp.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.vbonev.flickrdemoapp.R;
import com.example.vbonev.flickrdemoapp.adapters.TweetAdapter;
import com.example.vbonev.flickrdemoapp.model.TweetStatus;
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
    private TweetAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_feed);
        setTitle(getString(R.string.tweet_search_title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        twitterClient = new TwitterClient(this);
        RecyclerView tweetList = findViewById(R.id.tweet_list);
        tweetList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TweetAdapter();
        tweetList.setAdapter(adapter);
        getTweets("Elon Musk");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_tweet, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // use this method when query submitted
                if (query.length() < 4) {
                    showErrorMessage("Please input valid query");
                } else {
                    getTweets(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // use this method for auto complete search process
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private void showErrorMessage(String errorMessage) {
        Snackbar snackbar = Snackbar.make(getWindow().getDecorView(), errorMessage, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(ContextCompat.getColor(TwitterFeedActivity.this, android.R.color.holo_red_light));
        snackbar.show();
    }

    private void getTweets(String query) {
        twitterClient.searchQueryTweets(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  JSONObject json) {
                Log.d("DEBUG", "response: " + json.toString());
                try {
                    JSONArray tweets = json.getJSONArray("statuses");
                    List<TweetStatus> statuses = new ArrayList();
                    for (int i = 0; i < tweets.length(); i++) {
                        statuses.add(new TweetStatus(tweets.getJSONObject(i)));
                    }
                    adapter.setStatuses(statuses);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                showErrorMessage("Couldn't load tweets");
            }
        }, query, 100);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(TwitterFeedActivity.this, LandingActivity.class);
                startActivity(i);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, LandingActivity.class);
        startActivity(i);
    }
}
