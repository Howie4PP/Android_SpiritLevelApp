package com.example.shenhaichen.spiritlevelapp;

import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SpiritView spiritView;
    private SensorManager mSensorManager;
    private float[] accelerometerValues = new float[3];
    private float[] magneticFieldValues = new float[3];
    private float values[] = new float[3];
    private float[] inR = new float[9];
    private float[] inclineMatrix = new float[9];
    private TextView degree_tv;
    private float degree = 0f;
    private DecimalFormat df;
    private float coordinateX = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spiritView = (SpiritView) findViewById(R.id.mySpiritView);
        degree_tv = (TextView) this.findViewById(R.id.degree_tv);
        // get the sensor of the system
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        df = new DecimalFormat("#.#°");
    }

    @Override
    protected void onResume() {
        super.onResume();
    
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                Sensor.TYPE_ACCELEROMETER);
    
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                Sensor.TYPE_MAGNETIC_FIELD);

//        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_),
//                SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() != MotionEvent.ACTION_UP) {
            return super.onTouchEvent(event);
        }




        return super.onTouchEvent(event);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accelerometerValues = event.values;
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            magneticFieldValues = event.values;
        }
        // calculate the orientation of the sensor
        calculateOrientation();
        int orientation = getResources().getConfiguration().orientation;


        int sensorType = event.sensor.getType();
        switch (sensorType) {

            case Sensor.TYPE_ACCELEROMETER:

                switch (orientation) {
                    case Configuration.ORIENTATION_PORTRAIT:
                        spiritView.setCycleX(0);
                        if ((0 < values[0] && values[0] < 9.81) && (0 < values[1] && values[1] < 9.81)) {
                            spiritView.setCycleX(-(degree - 90) * 10);
                        } else if ((-9.81 < values[0] && values[0] < 0) && (0 < values[1] && values[1] < 9.81)) {
                            spiritView.setCycleX((degree - 90) * 10);
                        }
                        break;

                    case Configuration.ORIENTATION_LANDSCAPE:
                        spiritView.setCycleX(0);
                        if ((0 < values[0] && values[0] < 9.81) && (-9.81 < values[1] && values[1] < 0)) {
                            spiritView.setCycleX(degree * 10);
                        }else  if ((0 < values[0] && values[0] < 9.81) && (0 < values[1] && values[1] < 9.81)){
                            spiritView.setCycleX(-degree * 10);
                        }

                        break;
                }
                break;
        }
    }

    private void calculateOrientation() {
        SensorManager.getRotationMatrix(inR, inclineMatrix, accelerometerValues, magneticFieldValues);
        SensorManager.getOrientation(inR, values);
        degree = (float) Math.toDegrees(values[1] - 0);
        degree = Math.abs(degree);
//        float de = 90 - degree;
        degree_tv.setText(df.format(degree));

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
