package com.example.vbonev.flickrdemoapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.vbonev.flickrdemoapp.fragments.ImageFragment;
import com.example.vbonev.flickrdemoapp.model.FlickrPhoto;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private List<FlickrPhoto> photos;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        this.photos = new ArrayList<>();
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

    public void setPhotos(List<FlickrPhoto> photos) {
        this.photos = photos;
        notifyDataSetChanged();
    }
}