package com.example.app

import android.app.Application

class TaskIntentApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        TaskRepository.initialize(this)
    }
}