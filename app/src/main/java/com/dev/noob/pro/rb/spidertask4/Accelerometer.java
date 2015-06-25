package com.dev.noob.pro.rb.spidertask4;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Accelerometer extends ActionBarActivity implements SensorEventListener{
    Integer x=0,l=0,t=500;
    TextView textview;
    Sensor accelerometer;
    SensorManager sensorManager;
    GraphView graph;
    long time;
    LineGraphSeries<DataPoint> graphSeries= new LineGraphSeries<>(new DataPoint[]{});
    LineGraphSeries<DataPoint> graphSeries2= new LineGraphSeries<>(new DataPoint[]{});
    LineGraphSeries<DataPoint> graphSeries3= new LineGraphSeries<>(new DataPoint[]{});

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        textview = (TextView)findViewById(R.id.textview);
        graph = (GraphView)findViewById(R.id.graphview);
        graph.getViewport().setMaxX(30);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setScrollable(true);
        //graph.getViewport().setMaxX(5);
        //graphSeries.setDrawDataPoints(true);
        //graphSeries.setDataPointsRadius(10);
        graphSeries.setBackgroundColor(Color.BLUE);
        graphSeries.setColor(Color.BLACK);
        graphSeries2.setColor(Color.CYAN);
        graphSeries3.setColor(Color.MAGENTA);
        graph.setBackgroundColor(Color.BLUE);
        graph.addSeries(graphSeries);
        graph.addSeries(graphSeries2);
        graph.addSeries(graphSeries3);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_UI);
        time = System.currentTimeMillis();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accelerometer, menu);
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
    public void onSensorChanged(final SensorEvent event) {
        t+=100;
        Sensor sensor = event.sensor;
        if(sensor.getType()==Sensor.TYPE_ACCELEROMETER)
        {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after ts
                    graphSeries.appendData(new DataPoint(x,event.values[0]),false,10000);
                    graphSeries2.appendData(new DataPoint(x,event.values[1]),false,10000);
                    graphSeries3.appendData(new DataPoint(x,event.values[2]),false,10000);
                    textview.setText("" + event.values[0] + "\n" + event.values[1] + "\n" + event.values[2]);
                    x++;
                    l++;
                    if(x>=30)
                    {
                        graph.getViewport().scrollToEnd();
                    }

                }
            },t);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
