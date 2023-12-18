package com.example.dbtest

import android.app.Application
import com.example.dbtest.database.TaskDatabase
import com.example.dbtest.database.TaskRepository

class TaskApplication: Application() {
    val database by lazy { TaskDatabase.getDatabase(this) }
    val repository by lazy { TaskRepository(database.taskDao(),database.courseDao(),database.buildingDao()) }
}