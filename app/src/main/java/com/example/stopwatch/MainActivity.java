package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private short seconds = 0;
    private  long milli =0L;

    // Is the stopwatch running?
    private boolean running=  false;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runTimer();
    }

    public void onStart(View view)
    {
        running = true;
    }

    public void onStop(View view)
    {
        running = false;
    }

    public void onReset(View view)
    {
        running = false;
        seconds=0;
        milli = 0;
    }


    private void runTimer()
    {


        final TextView timeView
                = findViewById(
                R.id.time_view);


        final Handler handler
                = new Handler();

        handler.post(new Runnable() {
            @Override

            public void run()
            {
                short hours = (short) (seconds / 3600);
                short minutes = (short) ((seconds % 3600) / 60);
                short secs = (short) (seconds % 60);
                short millisecond = (short) (milli%100);


                String time
                        = String
                        .format(Locale.getDefault(),
                                "Hour      = %02d\n\nMinute  = %02d\n\nSecond = %02d \n\nMilliSec = %02d ", hours,
                                minutes, secs,millisecond);

                timeView.setText(time);

                if (running) {
                    milli++;
                }
                if(milli % 60 ==0 && running){
                    seconds++;
                }

                handler.postDelayed(this, 1);
            }
        });
    }
}