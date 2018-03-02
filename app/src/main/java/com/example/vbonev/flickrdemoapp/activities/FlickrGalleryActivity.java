package com.example.vbonev.flickrdemoapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.example.vbonev.flickrdemoapp.adapters.PagerAdapter;
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

public class FlickrGalleryActivity extends FragmentActivity {
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flickr_gallery);
        ViewPager galleryPager = findViewById(R.id.flickr_pager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        galleryPager.setAdapter(pagerAdapter);
        getPhotos();
    }

    private void getPhotos() {
        FlickrClient flickrClient = new FlickrClient(this);
        flickrClient.getRecentPhotos(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  JSONObject json) {
                Log.d("DEBUG", "result: " + json.toString());
                try {
                    JSONArray photos = json.getJSONObject("photos").getJSONArray("photo");
                    List<FlickrPhoto> photosList = new ArrayList<>();
                    for (int x = 0; x < photos.length(); x++) {
                        photosList.add(new FlickrPhoto(photos.getJSONObject(x)));
                    }
                    pagerAdapter.setPhotos(photosList);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("debug", e.toString());
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Snackbar snackbar = Snackbar.make(getWindow().getDecorView(), "Couldnt load images", Snackbar.LENGTH_LONG);
                View snackBarView = snackbar.getView();
                snackBarView.setBackgroundColor(ContextCompat.getColor(FlickrGalleryActivity.this, android.R.color.holo_red_light));
                snackbar.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, LandingActivity.class);
        startActivity(i);
    }
}
