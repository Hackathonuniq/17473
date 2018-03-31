package com.example.unique.myalarmmanagerapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*set Alarm*/

        String _date="2018-03-20 18:21:00";

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = (Date)formatter.parse(_date);
            date.getTime();
            startAlarm(date.getTime(),10);
            startAlarm(date.getTime()+30000,11);
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        Calendar calendar = Calendar.getInstance();
//
//        if(Build.VERSION.SDK_INT >= 23){
//            calendar.get(Calendar.YEAR),
//            calendar.get(Calendar.MONTH),
//            calendar.get(Calendar.DAY_OF_MONTH),
//            //Hours
//            //Minutes
//            0
//        };

        /*set Alarm Ends*/

    }

    private void startAlarm(long timeInMillis,int rcode) {
        AlarmManager alarmManager =(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent;
        PendingIntent pendingIntent;
        intent = new Intent(MainActivity.this,AlarmNotificationReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this,rcode,intent,0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeInMillis,pendingIntent);
    }
}
