package com.chandana.batterycheck;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.BatteryManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    IntentFilter intentFilter;
    ImageView batteryImage;
    TextView txtBatteryStatus,txtBatteryHealth,txtBatteryVoltage,txtBatteryTemp,txtBatteryTech,txtBatteryLevel;
    public Button start,stop,per;
    String percentage = "100";
    public int level;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        per = (Button) findViewById(R.id.buttonPer);
        start = (Button) findViewById(R.id.buttonstart);
        stop = (Button) findViewById(R.id.buttonstop);
        batteryImage = (ImageView) findViewById(R.id.batteryImage);
        txtBatteryStatus = (TextView) findViewById(R.id.txtStatus);
        txtBatteryHealth = (TextView) findViewById(R.id.txtHealth);
        txtBatteryVoltage = (TextView) findViewById(R.id.txtVoltage);
        txtBatteryTemp = (TextView) findViewById(R.id.txtTemp);
        txtBatteryTech = (TextView) findViewById(R.id.txtTech);
        txtBatteryLevel = (TextView) findViewById(R.id.txtLevel);


        intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

        final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onReceive(Context context, Intent intent) {
                //Status
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                        status == BatteryManager.BATTERY_STATUS_FULL;
                if(status == BatteryManager.BATTERY_STATUS_FULL)
                    txtBatteryStatus.setText(getString(R.string.fullcharge));
                else {
                    if (isCharging)
                        txtBatteryStatus.setText(getString(R.string.charging));
                    else
                        txtBatteryStatus.setText(getString(R.string.notcharging));
                }

                //Level
                level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
                txtBatteryLevel.setText(level+"%");

                //Image
                Resources res = context.getResources();

                if(level >= 90)
                {
                    batteryImage.setImageDrawable(res.getDrawable(R.drawable.cbattery1));

                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.highgreen)));
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Window window = getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.setStatusBarColor(getResources().getColor(R.color.darkhighgreen));
                    }

                        start.setBackgroundColor(getResources().getColor(R.color.highgreen));
                        stop.setBackgroundColor(getResources().getColor(R.color.highgreen));
                        per.setBackgroundColor(getResources().getColor(R.color.highgreen));
                        txtBatteryLevel.setTextColor(getResources().getColor(R.color.highgreen));
                        txtBatteryHealth.setTextColor(getResources().getColor(R.color.highgreen));
                        txtBatteryStatus.setTextColor(getResources().getColor(R.color.highgreen));
                        txtBatteryTech.setTextColor(getResources().getColor(R.color.highgreen));
                        txtBatteryTemp.setTextColor(getResources().getColor(R.color.highgreen));
                        txtBatteryVoltage.setTextColor(getResources().getColor(R.color.highgreen));
                        txtBatteryVoltage.setTextColor(getResources().getColor(R.color.highgreen));

                }
                else if(90 > level && level >= 65)
                {
                    batteryImage.setImageDrawable(res.getDrawable(R.drawable.cbattery2));

                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.green)));
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Window window = getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.setStatusBarColor(getResources().getColor(R.color.darkgreen));
                    }
                        start.setBackgroundColor(getResources().getColor(R.color.green));
                        stop.setBackgroundColor(getResources().getColor(R.color.green));
                        per.setBackgroundColor(getResources().getColor(R.color.green));
                        txtBatteryLevel.setTextColor(getResources().getColor(R.color.green));
                        txtBatteryHealth.setTextColor(getResources().getColor(R.color.green));
                        txtBatteryStatus.setTextColor(getResources().getColor(R.color.green));
                        txtBatteryTech.setTextColor(getResources().getColor(R.color.green));
                        txtBatteryTemp.setTextColor(getResources().getColor(R.color.green));
                        txtBatteryVoltage.setTextColor(getResources().getColor(R.color.green));
                        txtBatteryVoltage.setTextColor(getResources().getColor(R.color.green));

                    }
                else if(65 > level && level >= 40)
                {
                    batteryImage.setImageDrawable(res.getDrawable(R.drawable.cbattery3));

                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange)));
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Window window = getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.setStatusBarColor(getResources().getColor(R.color.darkorange));
                    }
                        start.setBackgroundColor(getResources().getColor(R.color.orange));
                        stop.setBackgroundColor(getResources().getColor(R.color.orange));
                        per.setBackgroundColor(getResources().getColor(R.color.orange));
                        txtBatteryLevel.setTextColor(getResources().getColor(R.color.orange));
                        txtBatteryHealth.setTextColor(getResources().getColor(R.color.orange));
                        txtBatteryStatus.setTextColor(getResources().getColor(R.color.orange));
                        txtBatteryTech.setTextColor(getResources().getColor(R.color.orange));
                        txtBatteryTemp.setTextColor(getResources().getColor(R.color.orange));
                        txtBatteryVoltage.setTextColor(getResources().getColor(R.color.orange));
                        txtBatteryVoltage.setTextColor(getResources().getColor(R.color.orange));

                    }
                else if(40 > level && level >= 15)
                {
                    batteryImage.setImageDrawable(res.getDrawable(R.drawable.cbattery4));

                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Window window = getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.setStatusBarColor(getResources().getColor(R.color.darkred));
                    }
                        start.setBackgroundColor(getResources().getColor(R.color.red));
                        stop.setBackgroundColor(getResources().getColor(R.color.red));
                        per.setBackgroundColor(getResources().getColor(R.color.red));
                        txtBatteryLevel.setTextColor(getResources().getColor(R.color.red));
                        txtBatteryHealth.setTextColor(getResources().getColor(R.color.red));
                        txtBatteryStatus.setTextColor(getResources().getColor(R.color.red));
                        txtBatteryTech.setTextColor(getResources().getColor(R.color.red));
                        txtBatteryTemp.setTextColor(getResources().getColor(R.color.red));
                        txtBatteryVoltage.setTextColor(getResources().getColor(R.color.red));
                        txtBatteryVoltage.setTextColor(getResources().getColor(R.color.red));

                    }
                else
                {
                    batteryImage.setImageDrawable(res.getDrawable(R.drawable.cbattery5));
                }

                //Voltage
                int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE,-1);
                txtBatteryVoltage.setText("BATTERY VOLTAGE : "+voltage+"mV");

                //Temperature
                int temp = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,-1);
                txtBatteryTemp.setText("BATTERY TEMPERATURE : "+temp+"*C");

                //Technology
                String tech = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);
                txtBatteryTech.setText("BATTERY TECHNOLOGY : "+tech);

                //Health
                int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH,-1);
                switch(health)
                {
                    case BatteryManager.BATTERY_HEALTH_COLD:
                        txtBatteryHealth.setText(getString(R.string.hcold));
                        break;
                    case BatteryManager.BATTERY_HEALTH_DEAD:
                        txtBatteryHealth.setText(getString(R.string.hdead));
                        break;
                    case BatteryManager.BATTERY_HEALTH_GOOD:
                        txtBatteryHealth.setText(getString(R.string.hgood));
                        break;
                    case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                        txtBatteryHealth.setText(getString(R.string.hoverheat));
                        break;
                    case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                        txtBatteryHealth.setText(getString(R.string.hovervoltage));
                        break;
                    case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                        txtBatteryHealth.setText(getString(R.string.hunknown));
                        break;
                    case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                        txtBatteryHealth.setText("BATTERY HEALTH : FAILURE");
                        break;
                    default:
                        break;
                }
            }
        };

        MainActivity.this.registerReceiver(broadcastReceiver, intentFilter);


    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.icon_activity, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if(id==R.id.helpicon)
        {
            startActivity(new Intent(MainActivity.this,pop.class));
        }

        return super.onOptionsItemSelected(item);
    }
    public void startSer(View v) {

        if((Integer.parseInt(percentage)) < level)
        {
            Toast.makeText(this, "Battery Is Over Charged!!", Toast.LENGTH_SHORT).show();
        }
        else if (txtBatteryStatus.getText().toString().equals("BATTERY STATUS : CHARGING")) {
            String input = percentage;


            Intent serviceIntent = new Intent(this, ExampleService.class);
            serviceIntent.putExtra("inputExtra", input);

            start.setVisibility(View.INVISIBLE);
            stop.setVisibility(View.VISIBLE);
            ContextCompat.startForegroundService(this, serviceIntent);
        }
        else
        {
            Toast.makeText(this, "Put Charging And Start Alarm!!", Toast.LENGTH_SHORT).show();
        }

    }

    public void stopSer(View v) {
        start.setVisibility(View.VISIBLE);
        stop.setVisibility(View.INVISIBLE);
        Intent serviceIntent = new Intent(this, ExampleService.class);
        stopService(serviceIntent);
    }

    public void stopService(){
        Intent serviceIntent = new Intent(this, ExampleService.class);
        stopService(serviceIntent);
    }

    public void showPopup(View v)
    {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch(menuItem.getItemId())
        {
            case R.id.item40:
                Toast.makeText(this, "Battery Alarm set for 40%", Toast.LENGTH_SHORT).show();
                percentage ="40";
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
                return true;

            case R.id.item50:
                Toast.makeText(this, "Battery Alarm set for 50%", Toast.LENGTH_SHORT).show();
                percentage ="50";
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
                return true;
            case R.id.item60:
                Toast.makeText(this, "Battery Alarm set for 60%", Toast.LENGTH_SHORT).show();
                percentage ="60";
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
                return true;
            case R.id.item70:
                Toast.makeText(this, "Battery Alarm set for 70%", Toast.LENGTH_SHORT).show();
                percentage ="70";
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
                return true;
            case R.id.item80:
                Toast.makeText(this, "Battery Alarm set for 80%", Toast.LENGTH_SHORT).show();
                percentage="80";
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
                return true;

            case R.id.item85:
                Toast.makeText(this, "Battery Alarm set for 85%", Toast.LENGTH_SHORT).show();
                percentage ="85";
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
                return true;
            case R.id.item90:
                Toast.makeText(this, "Battery Alarm set for 90%", Toast.LENGTH_SHORT).show();
                percentage ="90";
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
                return true;
            case R.id.item91:
                Toast.makeText(this, "Battery Alarm set for 91%", Toast.LENGTH_SHORT).show();
                percentage ="91";
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
                return true;
            case R.id.item92:
                Toast.makeText(this, "Battery Alarm set for 92%", Toast.LENGTH_SHORT).show();
                percentage ="92";
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
                return true;
            case R.id.item93:
                Toast.makeText(this, "Battery Alarm set for 93%", Toast.LENGTH_SHORT).show();
                percentage ="93";
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
                return true;
            case R.id.item94:
                Toast.makeText(this, "Battery Alarm set for 94%", Toast.LENGTH_SHORT).show();
                percentage ="94";
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
                return true;
            case R.id.item95:
                Toast.makeText(this, "Battery Alarm set for 95%", Toast.LENGTH_SHORT).show();
                percentage ="95";
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
                return true;
            case R.id.item96:
                Toast.makeText(this, "Battery Alarm set for 96%", Toast.LENGTH_SHORT).show();
                percentage ="96";
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
                return true;
            case R.id.item97:
                Toast.makeText(this, "Battery Alarm set for 97%", Toast.LENGTH_SHORT).show();
                percentage ="97";
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
                return true;

            case R.id.item98:
                Toast.makeText(this, "Battery Alarm set for 98%", Toast.LENGTH_SHORT).show();
                percentage ="98";
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
                return true;
            case R.id.item99:
                Toast.makeText(this, "Battery Alarm set for 99%", Toast.LENGTH_SHORT).show();
                percentage ="99";
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
                return true;

            case R.id.item100:
                Toast.makeText(this, "Battery Alarm set for Full Battery", Toast.LENGTH_SHORT).show();
                percentage ="100";
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
                return true;
            default:
                return false;

        }

    }
}