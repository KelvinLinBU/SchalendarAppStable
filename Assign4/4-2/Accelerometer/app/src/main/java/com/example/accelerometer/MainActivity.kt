package com.example.accelerometer

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import android.util.Log
import android.widget.Toast


class MainActivity : AppCompatActivity(),SensorEventListener {

    private lateinit var sensorManager: SensorManager
    lateinit var sbacc: SeekBar
    lateinit var accval: TextView
    private var lastX = 0f
    private var lastY = 0f
    private var lastZ = 0f
    private var lastUpdateTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sbacc = findViewById(R.id.sBacc) as SeekBar
        accval = findViewById<TextView>(R.id.accval) as TextView
        sbacc.progress = 5
        setUpSensorStuff()
        sbacc.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                accval.text = progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }
    private fun setUpSensorStuff(){
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also{
            sensorManager.registerListener(
                this,
                it,
                SensorManager.SENSOR_DELAY_NORMAL,
                SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val now = System.currentTimeMillis()
        if (now - lastUpdateTime < 800) {
            return
        }
        if(event?.sensor?.type == Sensor.TYPE_ACCELEROMETER){
            val x = kotlin.math.abs(lastX-event.values[0])
            val y = kotlin.math.abs(lastY-event.values[1])
            val z = kotlin.math.abs(lastZ-event.values[2])
            if (x>=sbacc.progress||y>=sbacc.progress||z>=sbacc.progress){
                lastUpdateTime = now
                Log.d("MotionDetection", "Significant Movement Detected!")
                Toast.makeText(applicationContext, "Significant Movement Detected!!", Toast.LENGTH_SHORT).show()
                lastX = event.values[0]
                lastY = event.values[1]
                lastZ = event.values[2]
            }
        }
    }

    override fun onAccuracyChanged(event: Sensor?, p1: Int) {
        return
    }

    override fun onDestroy() {
        sensorManager.unregisterListener(this)
        super.onDestroy()
    }
}