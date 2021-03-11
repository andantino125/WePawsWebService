package com.example.wepawswebservice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

//add below in build.gradle(:app)
// implementation 'com.squareup.okhttp3:okhttp:3.12.0'

public class WebServiceManager {

    //web service url: https://wepaws.azurewebsites.net/clinicws.asmx/get_clinic_listGet
    //method: POST
    //input json body: clinic_name, district, overnight
    //return columns in json format:  clinic_id, clinic_name, clinic_name_cn, clinic_address, clinic_address_cn,
    // clinic_address, clinic_address_cn, district, phone, overnight,
    // negative_count, neutral_count, positive_count, review_count

    public List<Clinic_Master> get_clinic_list(String clinic_name, String district, String overnight) {
        List<Clinic_Master> clinicList = null;

        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            String jsonContent = new JSONObject()
                    .put("clinic_name", clinic_name)
                    .put("district", district)
                    .put("overnight", overnight)
                    .toString();
            RequestBody body = RequestBody.create(mediaType, jsonContent);
            Request request = new Request.Builder()
                    .url("https://wepaws.azurewebsites.net/clinicws.asmx/get_clinic_list")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            //data = response.body().string();

            JSONArray arr = new JSONArray(response.body().string());
            clinicList = new ArrayList<>();

            for (int i = 0; i < arr.length(); i++) {
                clinicList.add(new Clinic_Master(arr.getJSONObject(i)));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return clinicList;
    }

    //web service url: https://wepaws.azurewebsites.net/clinicws.asmx/get_clinic_review
    //method: POST
    //input json body: clinic_id
    //return columns in json format: clinic_id, review, status
    public List<Clinic_Review> get_clinic_review(String clinic_id) {
        List<Clinic_Review> clinicReviewList = null;

        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            String jsonContent = new JSONObject()
                    .put("clinic_id", clinic_id)
                    .toString();
            RequestBody body = RequestBody.create(mediaType, jsonContent);
            Request request = new Request.Builder()
                    .url("https://wepaws.azurewebsites.net/clinicws.asmx/get_clinic_review")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            //data = response.body().string();

            JSONArray arr = new JSONArray(response.body().string());
            clinicReviewList = new ArrayList<>();

            for (int i = 0; i < arr.length(); i++) {
                clinicReviewList.add(new Clinic_Review(arr.getJSONObject(i)));
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return clinicReviewList;
    }

    public String add_clinic_review_rating(String clinic_id, String login, String review, String rate) {
        String result = null;

        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            String jsonContent = new JSONObject()
                    .put("clinic_id", clinic_id)
                    .put("login", login)
                    .put("review", review)
                    .put("rate", rate)
                    .toString();
            RequestBody body = RequestBody.create(mediaType, jsonContent);
            Request request = new Request.Builder()
                    .url("https://wepaws.azurewebsites.net/clinicws.asmx/add_clinic_review_rating")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            result = response.body().string();

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
