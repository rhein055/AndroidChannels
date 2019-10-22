package com.example.covertchannelfile_app1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.os.Bundle;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.Manifest;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int REQUEST_READ_PHONE_STATE = 0x01;
        try {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        TextView imeiText = (TextView)findViewById(R.id.imeiTextView);

        imeiText.setText("Getting permission...");

        while(true) {
            String imeiNumber = getIMEI();
            if (!imeiNumber.equals("")) {
                imeiText.setText(imeiNumber);
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    String getIMEI() {
        String imeiNumber = "";

        TextView imeiText = (TextView)findViewById(R.id.imeiTextView);
        int result = this.checkSelfPermission(Manifest.permission.READ_PHONE_STATE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            imeiText.setText("TEST2");
            TelephonyManager telMan = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
            imeiNumber = telMan.getDeviceId();
        }

        return  imeiNumber;
    }


}
