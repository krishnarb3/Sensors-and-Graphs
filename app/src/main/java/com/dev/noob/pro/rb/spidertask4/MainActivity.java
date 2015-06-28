package com.dev.noob.pro.rb.spidertask4;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.jjoe64.graphview.series.PointsGraphSeries;

import android.os.Handler;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity{
    SensorManager accelerometermanager,gyromanager,proximanager;
    Sensor accelerometersensor,gyrosensor,proxisensor;
    ImageView accelerometer,gyro,proxi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accelerometer = (ImageView)findViewById(R.id.imageView);
        gyro = (ImageView)findViewById(R.id.imageView2);
        proxi = (ImageView)findViewById(R.id.imageView3);
        accelerometermanager = (SensorManager)getSystemService(SENSOR_SERVICE);
        gyromanager = (SensorManager)getSystemService(SENSOR_SERVICE);
        proximanager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accelerometersensor = accelerometermanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                if(accelerometersensor==null)
                    Toast.makeText(MainActivity.this,"No accelerometer found",Toast.LENGTH_SHORT).show();
                else {
                    Intent i = new Intent(MainActivity.this, Accelerometer.class);
                    startActivity(i);
                }
            }
        });
        gyro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gyrosensor = gyromanager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
                if(gyrosensor==null)
                    Toast.makeText(MainActivity.this,"No gyrosensor found",Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(MainActivity.this, gyrosensor.class);
                    startActivity(intent);
                }
            }
        });
        proxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proxisensor = proximanager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
                if(proxisensor==null)
                    Toast.makeText(MainActivity.this,"No proximity sensor found",Toast.LENGTH_SHORT).show();
                else {
                    Intent intent2 = new Intent(MainActivity.this, proximity.class);
                    startActivity(intent2);
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
