package com.example.sofie.transenlog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    private Shock shock;

    private List<Shock> listOfShocks;

    private float lastX, lastY, lastZ;
    TextView test;
    SQLiteDatabase mydatabase;
    DatabaseHandler db;

    private float deltaX = 0;
    private float deltaY = 0;
    private float deltaZ = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(this);

/*        listOfShocks = db.getAllShocks();
        for (Shock mocks : listOfShocks){
            Log.v(String.valueOf(mocks.getId()), "");
        }*/



         //  mydatabase = openOrCreateDatabase("Shock", MODE_PRIVATE, null);



       //db.onCreate(mydatabase);


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                handleShakeEvent(count);
            }
        });
    }

    private void handleShakeEvent(int count) {
        Context context = getApplicationContext();
        CharSequence text = "Shake it!";
        int duration = Toast.LENGTH_SHORT;
        test = findViewById(R.id.test);
        test.setText(""+ count);



        shock = new Shock();
        shock.setTime("14-03-2014");
        shock.setShockStrenght(22);

        db.addShoks(shock);



        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onSensorChanged(SensorEvent event){


        deltaX = Math.abs(lastX - event.values[0]);
        deltaY = Math.abs(lastY - event.values[1]);
        deltaZ = Math.abs(lastZ - event.values[2]);

        if (deltaX < 2)
            deltaX = 0;
        if (deltaY < 2)
            deltaY = 0;
        if (deltaZ < 2)
            deltaZ = 0;

       // float speed = Math.abs(deltaX+deltaY+deltaZ - lastX - lastY - lastX) / diffTime * 10000;

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


/*    public void displayMaxValues(){

    }*/




    @Override
    public void onResume(){
        super.onResume();
        mSensorManager.registerListener((SensorEventListener) mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause(){
        mSensorManager.unregisterListener((SensorEventListener) mShakeDetector);
        super.onPause();
    }
}