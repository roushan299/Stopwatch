package com.Alwish.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.os.Handler;
import android.widget.TextView;
import java.util.Locale;

 public class MainActivity extends Activity {
  private boolean running;
  private boolean wasRunning;
  private int seconds;

  @Override
  protected void onCreate(Bundle savedInstaceState) {
   super.onCreate(savedInstaceState);
   setContentView(R.layout.activity_main);
   if (savedInstaceState != null) {
    seconds = savedInstaceState.getInt("seconds");
    running = savedInstaceState.getBoolean("running");
    wasRunning = savedInstaceState.getBoolean("wasRunning");
   }
   runTimer();
  }

  @Override
  public void onSaveInstanceState(Bundle savedInstanceState) {
   savedInstanceState.putInt("seconds", seconds);
   savedInstanceState.putBoolean("running", running);
   savedInstanceState.putBoolean("waRunning", wasRunning);
  }

  @Override
  protected void onPause() {
   super.onPause();
   wasRunning = running;
   running = false;
  }

  @Override
  protected void onResume() {
   super.onResume();
   wasRunning = running;
   running = true;
  }

  public void Start(View view) {
   running = true;
  }

  public void Stop(View view){
   running = false;
  }
  public void Reset(View view){
   seconds = 0;
   running = false;
  }
  private void runTimer(){
   final TextView timeView = (TextView) findViewById(R.id.time_view);
   final Handler handler = new Handler();
   handler.post(new Runnable() {
    @Override
    public void run() {
     int hours = seconds/3600;
     int minutes = (seconds%3600)/60;
     int secs = seconds%60;
     String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
     timeView.setText(time);
     if (running){
      seconds++;
     }
     handler.postDelayed(this, 1000);
    }
   });
  }
 }