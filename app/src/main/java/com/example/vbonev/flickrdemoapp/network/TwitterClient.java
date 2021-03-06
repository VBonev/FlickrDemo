package com.example.vbonev.flickrdemoapp.network;

import android.content.Context;
import android.util.Log;

import com.codepath.oauth.OAuthBaseClient;
import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.api.BaseApi;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class TwitterClient extends OAuthBaseClient {
    private static final BaseApi REST_API_INSTANCE = TwitterApi.instance();
    private static final String REST_URL = "https://api.twitter.com/1.1/";
    private static final String REST_CONSUMER_KEY = "hgNUiFIAQAkidkMOhTFcULjK9";
    private static final String REST_CONSUMER_SECRET = "Ttwk6aqw5m0XhaOrekJ1f68PJPGsjvY21jOk3o6UiwhBDe0l0X";
    private static final String REST_CALLBACK_URL = "oauth://twitterApp";

    public TwitterClient(Context context) {
        super(context, REST_API_INSTANCE, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }

    public void searchQueryTweets(AsyncHttpResponseHandler handler, String query,int count) {
        String apiUrl = getApiUrl("search/tweets.json");
        RequestParams params = new RequestParams();
        params.put("q", query);
        params.put("count", count);
        Log.d("DEBUG", "Sending API call to " + apiUrl);
        client.get(apiUrl, params, handler);
    }

}
