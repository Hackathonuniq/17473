package com.example.unique.myalarmmanagerapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.PendingIntent;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by Saiyed Farhan on 17-Mar-18.
 */

public class AlarmNotificationReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
       /*Reverification*/

        Toast.makeText(context,"Alarm Rings.....",Toast.LENGTH_LONG).show();
       /*Reverification Ends*/
    }
}
