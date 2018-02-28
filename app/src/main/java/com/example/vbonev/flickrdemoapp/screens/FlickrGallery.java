package com.example.vbonev.flickrdemoapp.screens;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.vbonev.flickrdemoapp.PagerAdapter;
import com.example.vbonev.flickrdemoapp.R;
import com.example.vbonev.flickrdemoapp.model.FlickrPhoto;
import com.example.vbonev.flickrdemoapp.network.FlickrClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class FlickrGallery extends FragmentActivity {
    private ViewPager galleryPager;
    private FlickrClient flickrClient;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flickr_gallery);
        galleryPager = findViewById(R.id.flickr_pager);
        flickrClient = new FlickrClient(this);
        getPhotos();
    }

    private void getPhotos() {
        flickrClient.getRecentPohotos(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  JSONObject json) {
                Log.d("DEBUG", "result: " + json.toString());
                // Add new photos to SQLite
                try {
                    JSONArray photos = json.getJSONObject("photos").getJSONArray("photo");
                    List<FlickrPhoto> photosList = new ArrayList<>();
                    for (int x = 0; x < photos.length(); x++) {
                        FlickrPhoto photo = new FlickrPhoto(photos.getJSONObject(x));
                        photosList.add(photo);
                    }
                    pagerAdapter = new PagerAdapter(getSupportFragmentManager(), photosList);
                    galleryPager.setAdapter(pagerAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("debug", e.toString());
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
