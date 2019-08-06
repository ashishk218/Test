package com.example.test;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean flag=false;
   Button button;
    CountDownTimer c;

    public void onclick(View view) {
        if(flag)
        {
          button.setText("GO");
            button.setTextColor(Color.GREEN);
            c.cancel();
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText("00:00");
            SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
            seekBar.setProgress(0);
            flag=false;

        }
        else {
            flag=true;
            button.setTextColor(Color.RED);
            button.setText("STOP");
            final SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
            seekBar.setMax(600);
            int i = seekBar.getProgress();


           c= new CountDownTimer(i * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    int seconds = (int) millisUntilFinished / 1000;
                    int k = seconds;
                    int minutes = seconds / 60;
                    seconds = seconds - minutes * 60;
                    String min, sec;

                    if (minutes < 10) {
                        min = "0" + minutes;
                    } else {
                        min = "" + minutes;

                    }
                    if (seconds < 10) {
                        sec = "0" + seconds;
                    } else {
                        sec = "" + seconds;

                    }
                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setText(min + ":" + sec);
                    seekBar.setProgress(k);

                }

                @Override
                public void onFinish() {

                    //mediaPlayer.start();

                }
            }.start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView= (TextView) findViewById(R.id.textView);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(600);
        button= findViewById(R.id.button);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int minutes=progress/60;
                int seconds = progress-minutes*60;
                String min,sec;

                if(minutes<10)
                {
                    min="0"+minutes;
                }
                else{
                    min=""+minutes;

                }
                if(seconds<10)
                {
                    sec="0"+seconds;
                }
                else{
                    sec=""+seconds;

                }
                textView.setText(min+":"+sec);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
