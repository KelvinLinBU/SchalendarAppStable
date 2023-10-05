package com.example.temperatureconverter

import org.junit.Assert.*
import org.junit.Test

class TransTest{
    @Test
    fun celToFah_iscorrect(){
        val actualFah = Trans().celToFah(30.0)
        assertEquals(86.0, actualFah, 0.01)
    }
    @Test
    fun fah_ToCel_iscorrect(){
        val actualFah = Trans().fahToCel(86.0)
        assertEquals(30.0, actualFah, 0.01)
    }
}