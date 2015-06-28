package com.dev.noob.pro.rb.spidertask4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class animatedball extends ActionBarActivity implements SensorEventListener{
    SensorManager sensorManager;
    Sensor accelerometer;
    Bitmap ball;
    Integer t=16;
    float x,y,l,m;
    canvas ourview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        ourview = new canvas(this);
        setContentView(ourview);
    }
    public class canvas extends View
    {

        public canvas(Context context)
        {
            super(context);
            ball = BitmapFactory.decodeResource(getResources(),R.drawable.ball);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(Color.GRAY);
            Paint mycolor = new Paint();
            mycolor.setColor(Color.BLUE);
            l=((canvas.getWidth()/2)-(x/10)*canvas.getWidth());
            if(l>canvas.getWidth()-300)
                l=canvas.getWidth()-300;
            if(l<-75)
                l=-75;
            m=((canvas.getHeight()/2)+(y/10)*canvas.getHeight());
            if(m>canvas.getHeight()-300)
                m=canvas.getHeight()-300;
            if(m<-75)
                m=-75;
            canvas.drawBitmap(ball,l,m,null);
            invalidate();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_animatedball, menu);
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
    public void onSensorChanged(final SensorEvent event)
    {   t+=1;
        Sensor sensor = event.sensor;
        final android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after ts
                x=event.values[0];
                y=event.values[1];
            }
        },t);


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
