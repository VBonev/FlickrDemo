package com.example.vbonev.flickrdemoapp.network;

import android.content.Context;
import android.util.Log;

import com.codepath.oauth.OAuthBaseClient;
import com.github.scribejava.apis.FlickrApi;
import com.github.scribejava.core.builder.api.BaseApi;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class FlickrClient extends OAuthBaseClient {

    public static final BaseApi REST_API_INSTANCE = FlickrApi.instance();

    public static final String REST_URL = "https://www.flickr.com/services";

    public static final String REST_CONSUMER_KEY = "65ac2cd440614c7d345daaa4754b7630";

    public static final String REST_CONSUMER_SECRET = "374a5edb84b96674";

    public static final String REST_CALLBACK_URL = "oauth://cprest";

    public FlickrClient(Context context) {
        super(context, REST_API_INSTANCE, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET,
                REST_CALLBACK_URL);
        setBaseUrl("https://api.flickr.com/services/rest");
    }

    public void getRecentPohotos(AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("?format=json&nojsoncallback=1&method=flickr.interestingness.getList");
        Log.d("DEBUG", "Sending API call to " + apiUrl);
        client.get(apiUrl, null, handler);
    }
}