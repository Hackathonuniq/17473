package com.example.asmashaikh.myloginlogoutapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Main2ActivityButton = (Button)findViewById(R.id.LoginButton);
        Main2ActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(getApplicationContext(),Main2Activity.class);;
                //pass info to second page
                startActivity(loginIntent);
            }
        });
    }
}
