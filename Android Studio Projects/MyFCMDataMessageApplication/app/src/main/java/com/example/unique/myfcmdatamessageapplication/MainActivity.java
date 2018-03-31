package com.example.unique.myfcmdatamessageapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper dbh=new dbHelper(this,"pt",null,1);

        String json="[{\"timerid\":\"151515\",\"time\":\"51551\"},{\"timerid\":\"151215\",\"time\":\"51121\"}]";
        Gson gson=new Gson();
        pojoTimer[] arr_timer=gson.fromJson(json,pojoTimer[].class);


        //        List<pojoTimer> _arrTimer=new ArrayList<>();
//        pojoTimer p1=new pojoTimer();
//        p1.setTime("1554554");
//        p1.setTimerid("123");
//        _arrTimer.add(p1); // object added to array list
//        pojoTimer p2=new pojoTimer();
//        p2.setTime("155455456");
//        p2.setTimerid("1234");
//        _arrTimer.add(p2);

        dbh.insertSalesmen(arr_timer);
        for(pojoTimer pj:arr_timer){

            // Set Alarm
            pj.getTime();
            pj.getTimerid();
        }
        /*Receive Data Message FCM*/
//        FirebaseInstanceId fd=new FirebaseInstanceId();
        Toast.makeText(this,"Token :"+FirebaseInstanceId.getInstance().getToken(),Toast.LENGTH_LONG).show();
        TextView textView = (TextView) findViewById(R.id.textView);
        if(getIntent().getExtras() != null)
        {

        for(String key : getIntent().getExtras().keySet())
            {
                if(key.equals("timer"))
                {
                    textView.setText(getIntent().getExtras().getString(key));
                }
            }
        }
        /*Receive Data Message FCM Ends*/
    }
}

//https://www.youtube.com/watch?v=PGMt11DK2uo