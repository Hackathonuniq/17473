package com.example.unique.myjobschedulerapplication;

import android.os.AsyncTask;

/**
 * Created by Asma Shaikh on 18-Mar-18.
 */

public class async_task_job extends AsyncTask <Void, Void, String>{

    @Override
    protected String doInBackground(Void... voids) {
        return "Background Long Running Task Finishes";
    }

}
