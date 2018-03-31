package com.example.unique.myjobschedulerapplication;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.widget.Toast;

/**
 * Created by Asma Shaikh on 18-Mar-18.
 */

public class jobScheduler extends JobService {

    private async_task_job my_async_task_job;

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {

        my_async_task_job = new async_task_job(){

            @Override
            protected void onPostExecute(String s) {
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                jobFinished(jobParameters,false);
            }
        };
        my_async_task_job.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        my_async_task_job.cancel(true);
        return false;
    }
}
