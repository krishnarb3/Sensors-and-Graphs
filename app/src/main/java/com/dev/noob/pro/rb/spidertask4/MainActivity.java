package com.dev.noob.pro.rb.spidertask4;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements SensorEventListener{
    TextView textview,textview2,textview3;
    Sensor accelerometer,gyrosensor,proximity;
    SensorManager sensorManager,sensorManager2,sensorManager3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = (TextView)findViewById(R.id.textview);
        textview2 = (TextView)findViewById(R.id.textview2);
        textview3 = (TextView)findViewById(R.id.textview3);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensorManager2 = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensorManager3 = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyrosensor= sensorManager2.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        proximity= sensorManager3.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager2.registerListener(this,gyrosensor,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager3.registerListener(this,proximity,SensorManager.SENSOR_DELAY_NORMAL);

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

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        if(sensor.getType()==Sensor.TYPE_ACCELEROMETER)
        textview.setText("X : "+event.values[0]+"\nY : "+event.values[1]+"\nZ : "+event.values[2]);
        else if(sensor.getType()==Sensor.TYPE_GYROSCOPE)
        textview2.setText(""+event.values[0]+"\n"+event.values[1]+"\n"+event.values[2]);
        else if(sensor.getType()==Sensor.TYPE_PROXIMITY)
        textview3.setText(""+event.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
