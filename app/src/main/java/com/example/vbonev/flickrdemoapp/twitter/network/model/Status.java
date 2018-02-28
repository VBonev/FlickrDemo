package com.example.vbonev.flickrdemoapp.twitter.network.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Status {

    private String id;
    private String text;

    private String uathor;

    public Status(JSONObject object) {
        super();

        try {
            this.id = object.getString("id");
            this.text = object.getString("text");
            this.uathor = object.getJSONObject("user").getString("name");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getText() {
        return text;
    }

    public String getUathor() {
        return uathor;
    }
}
