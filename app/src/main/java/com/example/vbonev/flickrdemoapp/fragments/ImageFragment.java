package com.example.vbonev.flickrdemoapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vbonev.flickrdemoapp.R;
import com.squareup.picasso.Picasso;

public class ImageFragment extends Fragment {
    private static final String ARG_PARAM1 = "imgURL";

    public ImageFragment() {
    }

    public static ImageFragment newInstance(String url) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_image, container, false);
        ImageView pageView = contentView.findViewById(R.id.page_image);
        String url = getArguments().getString(ARG_PARAM1);
        Picasso.with(getContext()).load(url).into(pageView);
        return contentView;
    }
}
