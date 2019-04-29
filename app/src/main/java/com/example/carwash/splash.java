package com.example.carwash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class splash extends AppCompatActivity {

    ImageView logo;
    int SPLASH_TIMEOUT = 1500;
    TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        logo = (ImageView) findViewById(R.id.logo);
        version = (TextView) findViewById(R.id.version);



    }

    @Override
    public void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // implement intent here

                Intent intent = new Intent(getApplication(),signIn.class);
                startActivity(intent);
            }
        }, SPLASH_TIMEOUT);

    }



}
