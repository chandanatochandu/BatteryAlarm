package com.chandana.batterycheck;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

public class pop extends Activity {

    View view;
    int lev;
    IntentFilter intentFilter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = this.getWindow().getDecorView();

        setContentView(R.layout.popwindow);
        intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

        final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onReceive(Context context, Intent intent) {
                //Status
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                        status == BatteryManager.BATTERY_STATUS_FULL;
                //Level
                lev = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);

                //Image
                Resources res = context.getResources();

                if (lev >= 90) {
                    view.setBackgroundResource(R.color.highgreen);
                } else if (90 > lev && lev >= 65) {
                    view.setBackgroundResource(R.color.green);
                } else if (65 > lev && lev >= 40) {
                    view.setBackgroundResource(R.color.orange);
                } else if (40 > lev && lev >= 15) {
                    view.setBackgroundResource(R.color.red);
                } else {
                    view.setBackgroundResource(R.color.colorPrimary);
                }

            }
            };

            this.registerReceiver(broadcastReceiver, intentFilter);



        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));
    }
}
