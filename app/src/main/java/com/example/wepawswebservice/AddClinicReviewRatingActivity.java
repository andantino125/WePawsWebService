package com.example.wepawswebservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AddClinicReviewRatingActivity extends AppCompatActivity {

    private ConnectivityManager mConnMgr;

    //Text View for displaying the downloaded JSON data
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clinic_review);

        mConnMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        mTextView = (TextView) findViewById(R.id.textView);
    }

    private boolean is_network_available() {
        if (mConnMgr != null) {
            NetworkInfo networkInfo = mConnMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                return true;
            }
        }
        Toast.makeText(this, "Network Not Available", Toast.LENGTH_LONG);
        return false;
    }

    public void add_clinic_review(View v) throws ExecutionException, InterruptedException {
        EditText et_clinic_id, et_review, et_rating;
        String clinic_id, login, review, rating;

        et_clinic_id = (EditText) findViewById(R.id.editTextClinicID);
        et_review = (EditText) findViewById(R.id.editTextReview);
        et_rating = (EditText) findViewById(R.id.editTextRating);

        clinic_id = et_clinic_id.getText().toString();
        login = "";
        review = et_review.getText().toString();
        rating = et_rating.getText().toString();

        if (is_network_available()) {
            //new AddClinicReviewTask().execute(clinic_id, login, review, rating);
            ExecutorService executor = Executors.newCachedThreadPool();
            AddClinicReviewCallable task = new AddClinicReviewCallable(clinic_id, login, review, rating);
            Future<String> result = executor.submit(task);
            mTextView.setText(result.get());

        }
    }

    private class AddClinicReviewCallable implements Callable<String> {

        String clinic_id, login, review, rating = null;

        public AddClinicReviewCallable(String param1, String param2, String param3, String param4) {
            clinic_id = param1;
            login = param2;
            review = param3;
            rating = param4;
        }

        public String call() {
            return new WebServiceManager().add_clinic_review_rating(clinic_id, login, review, rating);
        }
    }


}