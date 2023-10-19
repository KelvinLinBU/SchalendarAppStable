package com.example.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.widget.TextView
import android.view.animation.TranslateAnimation
import android.view.animation.Animation




class SouthActivity : AppCompatActivity(),SensorEventListener,GestureDetector.OnGestureListener {
    private lateinit var gestureDetector: GestureDetector
    private lateinit var sensorManager: SensorManager
    private var lastUpdateTime: Long = 0
    lateinit var text: TextView
    private var lastX = 0f
    private var lastY = 0f
    private var lastZ = 0f
    private var x1:Float=0.0f
    private var x2:Float=0.0f
    private var y1:Float=0.0f
    private var y2:Float=0.0f
    private val shake = 10

    companion object{
        const val MIN_DISTANCE = 150
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_south)
        text = findViewById<TextView>(R.id.South) as TextView
        setUpSensorStuff()
        gestureDetector = GestureDetector(this, this)
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


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gestureDetector.onTouchEvent(event!!)
        when(event?.action){
            0->{
                x1 = event.x
                y1 = event.y
            }
            1->{
                x2 = event.x
                y2 = event.y
                val dX:Float =x2-x1
                val dY:Float =y2-y1
                if(Math.abs(dX)> MIN_DISTANCE){
                    if(x2>x1){

                    }
                    else{

                    }
                }
                else if(Math.abs(dY)> MIN_DISTANCE){
                    if(y2>y1){

                    }
                    else{
                        startActivity(Intent(this@SouthActivity, MainActivity::class.java))
                    }
                }
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onFling(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean{
        return false
    }
    override fun onDown(p0: MotionEvent): Boolean {
        return false
    }
    override fun onShowPress(p0: MotionEvent) {
        return
    }
    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        return false
    }
    override fun onScroll(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        return false
    }
    override fun onLongPress(p0: MotionEvent) {
        return
    }
    private fun shakeText() {
        val textView = text

        val shakeAnimation = TranslateAnimation(0f, 10f, 0f, 10f)
        shakeAnimation.duration = 100
        shakeAnimation.repeatCount = 20
        shakeAnimation.repeatMode = Animation.REVERSE

        textView.startAnimation(shakeAnimation)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event?.sensor?.type == Sensor.TYPE_ACCELEROMETER){
            val now = System.currentTimeMillis()
            if (now - lastUpdateTime < 2000) {
                return
            }
            val x = kotlin.math.abs(lastX-event.values[0])
            val y = kotlin.math.abs(lastY-event.values[1])
            val z = kotlin.math.abs(lastZ-event.values[2])
            if (x+y+z>shake){
                lastUpdateTime = now
                shakeText()
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