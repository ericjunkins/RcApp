package com.example.lerrrrmps.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.lerrrrmps.myfirstapp.ControlStickView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity implements ControlStickView.JoystickListener{
    private Context context;
    private ControlStickView controlStickView;
    private ControlStickViewRight controlStickViewRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        controlStickView = new ControlStickView(context);
        controlStickViewRight = new ControlStickViewRight(context);
        setContentView(R.layout.activity_main);


    }

    @Override
    public void onJoystickMoved(float xPercent, float yPercent, int source) {
        Log.d("Main Method", "X percent: " + xPercent + "Y percent: " + yPercent);
    }
}
