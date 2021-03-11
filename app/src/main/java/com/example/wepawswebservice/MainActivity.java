package com.example.wepawswebservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Connectivity Manager instance
    private ConnectivityManager mConnMgr;

    //Text View for displaying the downloaded JSON data
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Toast.makeText(this, "Network Not Avaialble", Toast.LENGTH_LONG);
        return false;
    }

    public void goGetClinicList(View view) {
      //  String weight = vWeight.getText().toString();
        Intent intent = new Intent(this, GetClinicListActivity.class);
      //  Bundle bundle = new Bundle();
      //  bundle.putInt("feet", feet);
      //  bundle.putInt("inches", inches);
      //  bundle.putString("weight", weight);
      //  intent.putExtras(bundle);
        startActivity(intent);
    }

    public void goGetClinicReview(View view) {
       // String weight = vWeight.getText().toString();
        Intent intent = new Intent(this, GetClinicReviewActivity.class);
       // Bundle bundle = new Bundle();
       // bundle.putInt("feet", feet);
       // bundle.putInt("inches", inches);
       // bundle.putString("weight", weight);
       // intent.putExtras(bundle);
        startActivity(intent);
    }

    public void goAddClinicReviewRating(View view) {
       // String weight = vWeight.getText().toString();
        Intent intent = new Intent(this, AddClinicReviewRatingActivity.class);
       // Bundle bundle = new Bundle();
      //  bundle.putInt("feet", feet);
      //  bundle.putInt("inches", inches);
      //  bundle.putString("weight", weight);
      //  intent.putExtras(bundle);
        startActivity(intent);
    }
    /*
    public void get_clinic_list(View v) {
        EditText et_clinic_name, et_district, et_overnight;
        String clinic_name, district, overnight = "";

        et_clinic_name = (EditText) findViewById(R.id.editTextClinicName);
        et_district = (EditText) findViewById(R.id.editTextDistrict);
        et_overnight = (EditText) findViewById(R.id.editTextOvernight);

        clinic_name = et_clinic_name.getText().toString();
        district = et_district.getText().toString();
        overnight = et_overnight.getText().toString();

        if (is_network_available()) {
            new DownloadClinicList().execute(clinic_name, district, overnight);
        }
    }*/

    /*
    public void get_clinic_review_list(View v) {
        EditText et_clinic_id;
        String clinic_id;

        et_clinic_id = (EditText) findViewById(R.id.editTextClinicID);
        clinic_id = et_clinic_id.getText().toString();

        if (is_network_available()) {
            new DownloadClinicReviewList().execute(clinic_id);
        }
    }
*/
    /*
    private class DownloadClinicList extends AsyncTask<String, Void, List<Clinic_Master>> {

        @Override
        protected List<Clinic_Master> doInBackground(String... params) {
            return new WebService_Manager().get_clinic_list(params[0], params[1], params[2]);
        }

        protected void onPostExecute(List<Clinic_Master> clinicList) {

            String result = "";
            for (int i = 0; i < clinicList.size(); i++) {
                result += clinicList.get(i).clinic_id;
                result += " Name: " + clinicList.get(i).clinic_name + '\n';
                result += " Address: " + clinicList.get(i).clinic_address + '\n';
            }
            mTextView.setText(result);
        }
    }
*/
    /*
    private class DownloadClinicReviewList extends AsyncTask<String, Void, List<Clinic_Review>> {

        @Override
        protected List<Clinic_Review> doInBackground(String... params) {
            return new WebService_Manager().get_clinic_review(params[0]);
        }

        protected void onPostExecute(List<Clinic_Review> clinicReviewList) {
            String result = "";
            for (int i = 0; i < clinicReviewList.size(); i++) {
                result += clinicReviewList.get(i).clinic_id;
                result += " Review: " + clinicReviewList.get(i).review + '\n';
            }
            mTextView.setText(result);
        }
    }*/
}