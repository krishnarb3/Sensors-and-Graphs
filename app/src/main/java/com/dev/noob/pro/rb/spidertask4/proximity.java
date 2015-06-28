package com.dev.noob.pro.rb.spidertask4;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;


public class proximity extends ActionBarActivity implements SensorEventListener {
    public static String TAG="TAG";
    TextView textView;
    Integer d=5;
    Float hoopx,hoopy;
    Sensor proximity;
    ImageView ball,hoop;
    SensorManager sensorManager;
    ViewGroup layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);
        textView = (TextView)findViewById(R.id.textview);
        ball = (ImageView)findViewById(R.id.ball);
        hoop = (ImageView)findViewById(R.id.hoopimage);
        hoopx = hoop.getX();
        hoopy = hoop.getY();
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorManager.registerListener(this,proximity,SensorManager.SENSOR_DELAY_UI);
        layout = (ViewGroup)findViewById(R.id.proximitylayout);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_proximity, menu);
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
        TransitionManager.beginDelayedTransition(layout);
        textView.setText(event.values[0]+" (cm)");
        Log.d(TAG,event.values[0]+"CM" );
        if(event.values[0]==0.0)
        {
            TranslateAnimation translation = new TranslateAnimation(-250,65,-550,75);
            translation.setDuration(2000);
            translation.setFillAfter(true);
            ball.setAnimation(translation);
            //350,750

        }
        else
        {
            TranslateAnimation translation = new TranslateAnimation(65,-250,75,-550);
            translation.setDuration(2000);
            translation.setFillAfter(true);
            ball.setAnimation(translation);
            //75,75

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
