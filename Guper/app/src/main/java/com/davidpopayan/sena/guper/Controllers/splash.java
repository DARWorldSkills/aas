package com.davidpopayan.sena.guper.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.davidpopayan.sena.guper.R;

import java.util.Timer;
import java.util.TimerTask;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(splash.this, Login.class);
                startActivity(intent);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, 1000);


    }
}
