package vu.co.kaiyin.sensorsurvey;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        String sensorText = sensorList.stream().map(s -> s.getName() + "\n").collect(Collectors.joining());
        TextView sensorTextView = findViewById(R.id.sensor_list);
        sensorTextView.setText(sensorText);
    }


}
