package com.example.vbonev.flickrdemoapp.model;

import org.json.JSONException;
import org.json.JSONObject;

public class TweetModel {

    private String id;
    private String text;
    private String dateCreated;

    private String userName;
    private String screenName;
    private String userAvatar;

    private int favouriteCount;
    private int replyCount;

    public TweetModel(JSONObject object) {
        super();

        try {
            this.id = object.getString("id");
            this.text = object.getString("text");
            this.dateCreated = object.getString("created_at");

            this.userName = object.getJSONObject("user").getString("name");
            this.screenName = object.getJSONObject("user").getString("screen_name");
            this.userAvatar = object.getJSONObject("user").getString("profile_image_url_https");

            this.replyCount = object.getInt("reply_count");
            this.favouriteCount = object.getInt("favorite_count");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getText() {
        return text;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public String getScreenName() {
        return screenName;
    }

    public int getFavouriteCount() {
        return favouriteCount;
    }

    public int getReplyCount() {
        return replyCount;
    }
}
