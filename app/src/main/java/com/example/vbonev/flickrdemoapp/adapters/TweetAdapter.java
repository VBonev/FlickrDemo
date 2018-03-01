package com.example.vbonev.flickrdemoapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vbonev.flickrdemoapp.R;
import com.example.vbonev.flickrdemoapp.model.TweetStatus;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {
    private List<TweetStatus> statuses;

    static class ViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        TextView tweetScreenName;
        TextView tweetUserName;
        TextView tweetText;
        TextView tweetDateCreated;
        ImageView tweetUserAvatar;

        ViewHolder(View view) {
            super(view);
            tweetScreenName = view.findViewById(R.id.tweet_screen_name);
            tweetUserName = view.findViewById(R.id.tweet_user_name);
            tweetText = view.findViewById(R.id.tweet_text);
            tweetDateCreated = view.findViewById(R.id.tweet_date_created);
            tweetUserAvatar = view.findViewById(R.id.tweet_avatar);
        }
    }

    public TweetAdapter() {
        statuses=new ArrayList();
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public TweetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                      int viewType) {

        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweet_item_view, parent, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TweetStatus currentStatus = statuses.get(position);
        holder.tweetScreenName.setText(currentStatus.getScreenName());
        holder.tweetUserName.setText(currentStatus.getUserName());
        holder.tweetText.setText(currentStatus.getText());
        Linkify.addLinks( holder.tweetText, Linkify.ALL);
        holder.tweetDateCreated.setText(currentStatus.getDateCreated());
        Picasso.with(holder.itemView.getContext()).load(currentStatus.getUserAvatar()).into(holder.tweetUserAvatar);

    }

    @Override
    public int getItemCount() {
        return statuses.size();
    }

    public void setStatuses(List<TweetStatus> statuses) {
        this.statuses = statuses;
        notifyDataSetChanged();
    }
}