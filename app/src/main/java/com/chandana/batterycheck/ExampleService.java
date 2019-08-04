package com.chandana.batterycheck;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.BatteryManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Toast;

import static com.chandana.batterycheck.App.CHANNEL_ID;


import static java.lang.Thread.sleep;


public class ExampleService extends Service {

    public Ringtone player;
    int check;
    MainActivity m = new MainActivity();

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        String input = intent.getStringExtra("inputExtra");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Battery Alarm")
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_battery_charging_24dp)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);

        int inp = Integer.parseInt(input);
        doBackgroundWork(inp);


        return START_NOT_STICKY;
    }

    private void doBackgroundWork(int inp) {
        check = 0;
        if(player == null)
        {
            player = RingtoneManager.getRingtone(getApplicationContext(),
                    RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));
          //  player = (MediaPlayer) MediaPlayer.create(this, R.raw.song);
          /*  player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mysound) {
                    stopPlayer();
                }

            });*/
       }

        Toast.makeText(this,"Battery Alarm is ON",Toast.LENGTH_LONG).show();

        final int p =inp;

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 3*60*60*10; i++) {

                   IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

                   BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
                        @Override
                        public void onReceive(Context context, Intent intent) {
                            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                                    status == BatteryManager.BATTERY_STATUS_FULL;

                            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);

                            if (isCharging) {
                                if(level >= p ) {

                                    if(player!=null)
                                    player.play();
                                }
                        }
            //  /*
                          else{
                              // Intent serviceIntent = new Intent(ExampleService.this,MainActivity.class);
                                //stopService(serviceIntent);
                                m.stopService();
                                stopPlayer();


                        }
               // */
                        }
                    };

                    ExampleService.this.registerReceiver(broadcastReceiver, intentFilter);

                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                 //   if(check == 1)
                   //     break;
                }

                Thread.currentThread().interrupt();

            }
        }).start();
    }

    private void stopPlayer()
    {

        if(player!=null)
        {
            player.stop();
            player =null;
        }
    }
    @Override
    public void onDestroy() {
        Toast.makeText(this,"Battery Alarm is OFF",Toast.LENGTH_LONG).show();
        stopPlayer();
        super.onDestroy();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}