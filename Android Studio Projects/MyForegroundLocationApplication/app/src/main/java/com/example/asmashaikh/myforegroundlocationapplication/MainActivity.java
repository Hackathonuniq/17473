package com.example.asmashaikh.myforegroundlocationapplication;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    Button ForgroundServicebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ForgroundServicebtn = (Button) findViewById(R.id.foreground_service);
        if(isMyServiceRunning(com.example.asmashaikh.myforegroundlocationapplication.ForegroundService.class)){
            Toast.makeText(this,"serviec running",Toast.LENGTH_LONG).show();
            ForgroundServicebtn. setText("Log Out");
        }
        else{
            Toast.makeText(this,"serviec not running",Toast.LENGTH_SHORT).show();
            ForgroundServicebtn.setText("Log In");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isMyServiceRunning(com.example.asmashaikh.myforegroundlocationapplication.ForegroundService.class)){
            Toast.makeText(this,"serviec running",Toast.LENGTH_LONG).show();
            ForgroundServicebtn. setText("Log Out");
        }
        else{
            Toast.makeText(this,"serviec not running",Toast.LENGTH_SHORT).show();

            ForgroundServicebtn.setText("Log In");
        }
    }

    public void foregroundService(View view) {

        if(((Button)view.findViewById(R.id.foreground_service)).getText().toString().equals("Log Out")){
        //startForegroundService(new Intent(getApplicationContext(),ForegroundService.class));
            stopService(new Intent(getApplicationContext(), com.example.asmashaikh.myforegroundlocationapplication.ForegroundService.class));
            if(isMyServiceRunning(com.example.asmashaikh.myforegroundlocationapplication.ForegroundService.class)){
                Toast.makeText(this,"serviec running",Toast.LENGTH_LONG).show();

            }
            else{
                Toast.makeText(this,"service not running",Toast.LENGTH_SHORT).show();
            }
            ((Button)view.findViewById(R.id.foreground_service)).setText("Log In");
        }else {

            startService(new Intent(this, com.example.asmashaikh.myforegroundlocationapplication.ForegroundService.class).putExtra("foreground","fr"));
            if(isMyServiceRunning(com.example.asmashaikh.myforegroundlocationapplication.ForegroundService.class)){
                Toast.makeText(this,"serviec running",Toast.LENGTH_LONG).show();

            }
            else{
                Toast.makeText(this,"serviec not running",Toast.LENGTH_SHORT).show();
            }
            ((Button)view.findViewById(R.id.foreground_service)).setText("Log Out");
        }
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


}