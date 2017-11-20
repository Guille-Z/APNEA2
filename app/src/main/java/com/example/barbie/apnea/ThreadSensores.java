package com.example.barbie.apnea;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;


public class ThreadSensores implements SensorEventListener, Runnable {
    private SensorManager mSensorManager;
    private Handler mainThreadHandler;

    private DatosAcelerometro datosAcelerometro = new DatosAcelerometro();

    public ThreadSensores(SensorManager mSensorManager, Handler mainThreadHandler) {
        this.mSensorManager = mSensorManager;
        this.mainThreadHandler = mainThreadHandler;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //esto corre en el ui thread asi que tod.o es al pedo =D
        synchronized (this) {
            /*   TODO: cambiar a la activity que corresponde y descomentar
            if(!MainActivity.visible) {
                parar();
                return;
            }
            */
            switch (sensorEvent.sensor.getType() ) {
                case Sensor.TYPE_ACCELEROMETER:
                    datosAcelerometro.set(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
                    /*
                    mainThreadHandler.post(
                            new Runnable() {
                                @Override
                                public void run() {

                                    MainActivity.txt.setText(datosAcelerometro.toString());
                                    Log.d("Thread", "["+ Thread.currentThread().getId() + "]" + "Actualicé los datos");
                                }
                            }
                    );
                    */
                    //MainActivity.txt.setText(datosAcelerometro.toString()); // TODO: cambiar por la activity que va y descomentar
                    Log.d("Thread", "["+ Thread.currentThread().getId() + "]" + "Actualicé los datos");
                    break;
                case Sensor.TYPE_LIGHT:
                    break;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }



    @Override
    public void run() {
        escuchar();
        Log.d("Thread", "["+ Thread.currentThread().getId() + "]" + "Empecé a correr");
        while(!Thread.currentThread().isInterrupted()) {
            SystemClock.sleep(2000);
            Log.d("Thread", "["+ Thread.currentThread().getId() + "]" + "Todavia estoy vivo");
        }


    }

    private void escuchar() {
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),   SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),           SensorManager.SENSOR_DELAY_NORMAL);
        /*
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),       SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),     SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR), SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),  SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY),       SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE),     SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),         SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE),        SensorManager.SENSOR_DELAY_NORMAL);
        */
    }

    private void parar() {
        mSensorManager.unregisterListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
        mSensorManager.unregisterListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT));
        /*
        mSensorManager.unregisterListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE));
        mSensorManager.unregisterListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION));
        mSensorManager.unregisterListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD));
        mSensorManager.unregisterListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY));
        mSensorManager.unregisterListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE));
        mSensorManager.unregisterListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY));
        mSensorManager.unregisterListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR));
        mSensorManager.unregisterListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE));
        */
    }


}
