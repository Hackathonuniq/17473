package com.example.unique.myfcmupstreammessageapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private timer

    private final String TAG = "JSA-FCM";
    private final String SENDER_ID = "xxxxxxxxxx";
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_key1 = (EditText) findViewById(R.id.edt_key1);
        edt_key2 = (EditText) findViewById(R.id.edt_key2);
        edt_value1 = (EditText) findViewById(R.id.edt_value1);
        edt_value2 = (EditText) findViewById(R.id.edt_value2);

        btn_upmessage = (Button) findViewById(R.id.btn_upmessage);

        btn_upmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseMessaging fm = FirebaseMessaging.getInstance();

                RemoteMessage message = new RemoteMessage.Builder(SENDER_ID + "@gcm.googleapis.com")
                        .setMessageId(Integer.toString(random.nextInt(9999)))
                        .addData(edt_key1.getText().toString(), edt_value1.getText().toString())
                        .addData(edt_key2.getText().toString(), edt_value2.getText().toString())
                        .build();

                if (!message.getData().isEmpty()) {
                    Log.e(TAG, "UpstreamData: " + message.getData());
                }

                if (!message.getMessageId().isEmpty()) {
                    Log.e(TAG, "UpstreamMessageId: " + message.getMessageId());
                }

                fm.send(message);
            }
        });

    }
}