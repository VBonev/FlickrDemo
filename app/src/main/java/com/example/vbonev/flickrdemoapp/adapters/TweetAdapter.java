package com.example.vbonev.flickrdemoapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vbonev.flickrdemoapp.R;
import com.example.vbonev.flickrdemoapp.model.Status;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {
    private List<Status> statuses;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView tweetScreenName;
        TextView tweetUserName;
        TextView tweetText;
        TextView tweetDateCreated;
        ImageView tweetUserAvatar;

        TextView tweetLikeCount;
        TextView tweetCommentount;

        ViewHolder(View view) {
            super(view);
            tweetScreenName = view.findViewById(R.id.tweet_screen_name);
            tweetUserName = view.findViewById(R.id.tweet_user_name);
            tweetText = view.findViewById(R.id.tweet_text);
            tweetDateCreated = view.findViewById(R.id.tweet_date_created);
            tweetUserAvatar = view.findViewById(R.id.tweet_avatar);
            tweetLikeCount = view.findViewById(R.id.tweet_like_count);
            tweetCommentount = view.findViewById(R.id.tweet_comment_count);
        }
    }

    TweetAdapter(List<Status> statuses) {
        this.statuses = statuses;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public TweetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {

        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweet_item_view, parent, false);
        ViewHolder vh = new ViewHolder(mView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Status currentStatus = statuses.get(position);
        holder.tweetScreenName.setText(currentStatus.getScreenName());
        holder.tweetUserName.setText(currentStatus.getUserName());
        holder.tweetText.setText(currentStatus.getText());
        holder.tweetDateCreated.setText(currentStatus.getDateCreated());
        Picasso.with(holder.itemView.getContext()).load(currentStatus.getUserAvatar()).into(holder.tweetUserAvatar);

        holder.tweetLikeCount.setText(String.valueOf(currentStatus.getFavouriteCount()));
        holder.tweetCommentount.setText(String.valueOf(currentStatus.getReplyCount()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return statuses.size();
    }

}