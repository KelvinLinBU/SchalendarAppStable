package com.example.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.app.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task ORDER BY date,due")
    fun getTasks(): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE id=(:id)")
    suspend fun getTask(id: Int): Task

    @Update
    suspend fun updateTask(task: Task)

    @Insert
    suspend fun addTask(task: Task)
}
