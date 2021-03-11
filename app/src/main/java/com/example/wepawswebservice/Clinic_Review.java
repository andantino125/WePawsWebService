package com.example.wepawswebservice;

import org.json.JSONException;
import org.json.JSONObject;

public class Clinic_Review {
    int clinic_id;
    String login;
    String review;
    String status;

    public Clinic_Review(JSONObject jsonObject){
        try {
            clinic_id = jsonObject.getInt("clinic_id");
            review = jsonObject.getString("review");
            status = jsonObject.getString("status");
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }
}
