package com.example.asmashaikh.myforegroundlocationapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.asmashaikh.myforegroundlocationapplication.MainActivity;
import com.example.asmashaikh.myforegroundlocationapplication.R;

public class ForegroundService extends Service {


    private NotificationManager notificationManager;
    private ThreadGroup basicServiceThreads = new ThreadGroup("BasicServiceGroup");
    private boolean isShowingForegroundNotification;
    private Thread thread;

    public ForegroundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "onBind Called", Toast.LENGTH_SHORT).show();
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "onCreate Called", Toast.LENGTH_SHORT).show();
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Toast.makeText(this, "onStartCommand Called", Toast.LENGTH_SHORT).show();
        if (intent.hasExtra("foreground")) {
            //Foreground Service Demo
            if (isShowingForegroundNotification) {
                stopImportantJob();
                stopSelf();//you have to stop it still, it is not enough with stopforeground
            } else
                doImportantJob();
        } else {
            //Not foreground Service Demo
            String message = intent.getExtras().getString("extra");
            Log.v("BASICSERVICE", "onStartCommand() called, message = " + message + ", startId = " + startId);
            displayNotificationMessage("something is happening, it is not a foreground service");
            thread = new Thread(basicServiceThreads, new RunnableWorker(message), "BackgroundThread-1");
            thread.start();
        }
        return START_NOT_STICKY;
    }



    private void displayNotificationMessage(String message) {

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle(message)
                .setContentText("Touch to turn off service")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Starting up!!!")
                .setContentIntent(contentIntent)
                .setOngoing(false) //by default false
                .build();

        notificationManager.notify(0, notification);
    }

    void doImportantJob() {
        //...  perform important job
        //make this service a foreground service, so it will be as important as the Activity
        PendingIntent contentIntent =
                PendingIntent.getActivity(this, 0,
                        new Intent(this, MainActivity.class), 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("This notification is from a foreground service")
                .setContentText("Touch to open activity handling this service")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Starting up!!!")
                .setContentIntent(contentIntent)
                .setOngoing(false) //Always true in startForeground
                .build();
        startForeground(1992, notification); //notification can not be dismissed until detached,// or stopped service or stopForeground()
        isShowingForegroundNotification = true;
    }

    private void stopImportantJob() {
        //... Stop your work
        //notificationManager.cancel(1992); Will not work in the notification started with startForeground
        //notificationManager.cancelAll(); neither
        stopForeground(true);
        isShowingForegroundNotification = false;
        if (false) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                stopForeground(STOP_FOREGROUND_DETACH); //now you can dismiss the notification
                stopForeground(STOP_FOREGROUND_REMOVE);
            }
        }
    }

    class RunnableWorker implements Runnable {
        private String counter;

        public RunnableWorker(String msg) {
            this.counter = counter;
        }

        public void run() {
            final String TAG2 = "RunnableWorker:" + Thread.currentThread().getId();
            // do background processing here...
            try {
                Log.v(TAG2, "sleeping for 10 seconds. counter = " + counter);
                Thread.sleep(5000);
                Log.v(TAG2, "... waking up");
            } catch (InterruptedException e) {
                Log.v(TAG2, "... sleep interrupted");
            }
        }
    }


}
