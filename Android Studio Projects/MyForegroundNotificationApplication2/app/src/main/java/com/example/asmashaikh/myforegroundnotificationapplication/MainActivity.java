package com.example.asmashaikh.myforegroundnotificationapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "Foreground Service";
    public static int count = 0;
    private EditText etxtSubmit;

    private Button startForegroundServicebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startForegroundServicebtn = (Button) findViewById(R.id.start_foreground_service);
        startForegroundServicebtn.setOnClickListener(this);
    }

    public void startForeground(View view) {

        startService(new Intent(this, ForegroundService.class).putExtra("foreground", true));
    }

    public void stopForeground(View view) {
        startForegroundServicebtn.setText("Stop Foregroung Service");
        startService(new Intent(this, ForegroundService.class).putExtra("foreground", true));
    }


    @Override
    public void onClick(View view) {

    }
}
