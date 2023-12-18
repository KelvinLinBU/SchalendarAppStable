package com.example.app

import android.content.Context
import androidx.room.Room
import com.example.app.data.TaskDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

private const val DATABASE_NAME = "task-database"
class TaskRepository private constructor(
    context: Context,
    private val coroutineScope: CoroutineScope = GlobalScope
){
    private val database: TaskDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            TaskDatabase::class.java,
            DATABASE_NAME
        ).build()

    fun getTasks(): Flow<List<Task>> = database.taskDao().getTasks()
    suspend fun getTask(id:Int): Task = database.taskDao().getTask(id)
    fun updateTask(task: Task) {
        coroutineScope.launch { database.taskDao().updateTask(task) }
    }
    suspend fun addTask(task: Task) { database.taskDao().addTask(task) }


    companion object {
        private var INSTANCE: TaskRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = TaskRepository(context)
            }
        }

        fun get(): TaskRepository {
            return INSTANCE
                ?: throw IllegalStateException("TaskRepository must be initialized")
        }
    }
}