package com.example.temperatureconverter

class Trans {
    fun celToFah(cel: Double): Double {
        return (cel * 9/5) + 32
    }
    fun fahToCel(fah: Double): Double {
        return (fah - 32) * 5/9
    }
}