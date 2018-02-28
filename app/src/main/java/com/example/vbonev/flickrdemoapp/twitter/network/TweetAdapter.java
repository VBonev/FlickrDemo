package com.example.vbonev.flickrdemoapp.twitter.network;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vbonev.flickrdemoapp.R;
import com.example.vbonev.flickrdemoapp.twitter.network.model.Status;

import java.util.List;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {
    private List<Status> statuses;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            mTextView = view.findViewById(R.id.tweet_text);
        }
    }

    public TweetAdapter(List<Status> statuses) {
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
        holder.mTextView.setText(currentStatus.getText());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return statuses.size();
    }

}