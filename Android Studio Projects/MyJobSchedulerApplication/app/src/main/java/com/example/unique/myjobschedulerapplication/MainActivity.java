package com.example.unique.myjobschedulerapplication;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int job_ID = 100;
    private JobScheduler job_Scheduler;
    private JobInfo job_Info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ComponentName componentName = new ComponentName(this,jobScheduler.class);
        JobInfo.Builder builder = new JobInfo.Builder(job_ID,componentName);

        builder.setPeriodic(5000);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        builder.setPersisted(true);

        job_Info = builder.build();
        job_Scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
    }

    public void scheduleJob(View view) {
        job_Scheduler.schedule(job_Info);
        Toast.makeText(this,"Job Schedule",Toast.LENGTH_LONG).show();
    }

    public void clearJob(View view) {
        job_Scheduler.cancel(job_ID);
        Toast.makeText(this,"Job Cancelled",Toast.LENGTH_LONG).show();
    }
}
