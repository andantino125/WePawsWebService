package com.example.wepawswebservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class GetClinicReviewActivity extends AppCompatActivity {

    private ConnectivityManager mConnMgr;

    //Text View for displaying the downloaded JSON data
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_clinic_review);

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

    public void get_clinic_review_list(View v) {
        EditText et_clinic_id;
        String clinic_id;

        et_clinic_id = (EditText) findViewById(R.id.editTextClinicID);
        clinic_id = et_clinic_id.getText().toString();

        if (is_network_available()) {
            new DownloadClinicReviewList().execute(clinic_id);
        }
    }

    private class DownloadClinicReviewList extends AsyncTask<String, Void, List<Clinic_Review>> {

        @Override
        protected List<Clinic_Review> doInBackground(String... params) {
            return new WebServiceManager().get_clinic_review(params[0]);
        }

        protected void onPostExecute(List<Clinic_Review> clinicReviewList) {
            String result = "";
            for (int i = 0; i < clinicReviewList.size(); i++) {
                result += clinicReviewList.get(i).clinic_id;
                result += " Review: " + clinicReviewList.get(i).review + '\n';
            }
            mTextView.setText(result);
        }
    }
}