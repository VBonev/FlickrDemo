package com.example.vbonev.flickrdemoapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.vbonev.flickrdemoapp.model.FlickrPhoto;
import com.example.vbonev.flickrdemoapp.screens.ImageFragment;

import java.util.List;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private List<FlickrPhoto> photos;

    public PagerAdapter(FragmentManager fm, List<FlickrPhoto> photos) {
        super(fm);
        this.photos = photos;
    }

    @Override
    public Fragment getItem(int position) {
        //ImageFragment is a normal fragment containing a ImageView
        return ImageFragment.newInstance(photos.get(position).getUrl());
    }

    @Override
    public int getCount() {
        return photos.size();
    }

}