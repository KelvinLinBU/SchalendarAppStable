package com.example.myapplication.ui.course

import android.app.Application

class CourseIntentApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        CourseRepository.initialize(this)
    }
}