package com.example.vbonev.flickrdemoapp.model;

import org.json.JSONException;
import org.json.JSONObject;

public class FlickrPhoto {

    private String uid;

    private String name;

    private String url;

    public FlickrPhoto() {
        super();
    }

    public FlickrPhoto(JSONObject object) {
        super();

        try {
            this.uid = object.getString("id");
            this.name = object.getString("title");
            // http://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg
            this.url = "http://farm" + object.getInt("farm") + ".staticflickr.com/" + object
                    .getInt("server") +
                    "/" + uid + "_" + object.getString("secret") + ".jpg";
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getUid() {
        return uid;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

}
