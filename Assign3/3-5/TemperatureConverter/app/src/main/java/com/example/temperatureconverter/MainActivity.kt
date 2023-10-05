package com.example.temperatureconverter

import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    lateinit var sbcel: SeekBar
    lateinit var sbfah: SeekBar
    lateinit var celnum: TextView
    lateinit var fahnum: TextView

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sbcel = findViewById(R.id.sBcel) as SeekBar
        sbfah = findViewById(R.id.sBfah) as SeekBar
        celnum = findViewById<TextView>(R.id.celnum) as TextView
        fahnum = findViewById<TextView>(R.id.fahnum) as TextView
        sbfah.progress = 32
        sbcel.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                celnum.text = progress.toString() + "°C"
                val fah = Trans().celToFah(progress/1.0)
                sbfah.progress = fah.toInt()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar?.progress ?:0 < 20) {
                    val snackbar = Snackbar.make(findViewById(android.R.id.content), "I wish it were warmer.", Snackbar.LENGTH_SHORT)
                    snackbar.show()
                }
                else{
                    if (seekBar?.progress ?:0 > 30) {
                        val snackbar = Snackbar.make(findViewById(android.R.id.content), "I wish it were colder.", Snackbar.LENGTH_SHORT)
                        snackbar.show()
                    }
                }
            }
        })
        sbfah.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                fahnum.text = progress.toString() + "°F"
                val cel = Trans().fahToCel(progress/1.0)
                sbcel.progress = cel.toInt()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar?.progress ?:0 < 32){
                    seekBar?.progress = 32
                }
                else if (seekBar?.progress ?:0 < 68) {
                    val snackbar = Snackbar.make(findViewById(android.R.id.content), "I wish it were warmer.", Snackbar.LENGTH_SHORT)
                    snackbar.show()
                }
                else{
                    if (seekBar?.progress ?:0 > 86) {
                        val snackbar = Snackbar.make(findViewById(android.R.id.content), "I wish it were colder.", Snackbar.LENGTH_SHORT)
                        snackbar.show()
                    }
                }

            }
        })



    }
}


