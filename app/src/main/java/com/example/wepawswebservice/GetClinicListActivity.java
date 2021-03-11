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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GetClinicListActivity extends AppCompatActivity {

    private ConnectivityManager mConnMgr;

    //Text View for displaying the downloaded JSON data
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_clinic_list);

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

    public void get_clinic_list(View v) throws ExecutionException, InterruptedException {
        EditText et_clinic_name, et_district, et_overnight;
        String clinic_name, district, overnight = "";

        et_clinic_name = (EditText) findViewById(R.id.editTextClinicName);
        et_district = (EditText) findViewById(R.id.editTextDistrict);
        et_overnight = (EditText) findViewById(R.id.editTextOvernight);

        clinic_name = et_clinic_name.getText().toString();
        district = et_district.getText().toString();
        overnight = et_overnight.getText().toString();

        if (is_network_available()) {
            ExecutorService executor = Executors.newCachedThreadPool();
            GetClinicListCallable task = new GetClinicListCallable(clinic_name, district, overnight);
            Future<List<Clinic_Master>> future = executor.submit(task);
            List<Clinic_Master> clinicList = future.get();

            String result = "";
            for (int i = 0; i < clinicList.size(); i++) {
                result += clinicList.get(i).clinic_id;
                result += " Name: " + clinicList.get(i).clinic_name + '\n';
                result += " Address: " + clinicList.get(i).clinic_address + '\n';
            }
            mTextView.setText(result);
        }
    }

    private class GetClinicListCallable implements Callable<List<Clinic_Master>> {

        String clinic_name, district, overnight = null;

        public GetClinicListCallable(String param1, String param2, String param3) {
            clinic_name = param1;
            district = param2;
            overnight = param3;
        }

        public List<Clinic_Master> call() {
            return new WebServiceManager().get_clinic_list(clinic_name, district, overnight);
        }
    }

}