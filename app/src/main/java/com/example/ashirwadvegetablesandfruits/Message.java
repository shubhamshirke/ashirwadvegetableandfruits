package com.example.ashirwadvegetablesandfruits;

import android.Manifest;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Message extends AppCompatActivity {
    EditText message,number,count;
    Button contacts,sendsms,whatsappsms,sms_manually;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        message=findViewById(R.id.editText5);
        number=findViewById(R.id.editText6);
        count=findViewById(R.id.editText7);
        contacts=findViewById(R.id.button4);
        sendsms=findViewById(R.id.button7);
        whatsappsms=findViewById(R.id.button6);
        sms_manually=findViewById(R.id.button5);

        Dexter.withContext(this)
                .withPermission(Manifest.permission.SEND_SMS)
                .withListener(new PermissionListener() {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {/* ... */}
                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */}
                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
                }).check();

        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        sendsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    for (int i = 0; i <= Integer.parseInt(count.getText().toString()); i++) {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(number.getText().toString(), null, message.getText().toString(), null, null);
                        Toast.makeText(Message.this, "SMS Sent :count" + (i + 1), Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e)
                {
                    Log.d("name", "onClick: "+e);
                    Toast.makeText(Message.this,"SMS failed to Sent "+e,Toast.LENGTH_SHORT).show();
                }
            }
        });

        whatsappsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        sms_manually.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
