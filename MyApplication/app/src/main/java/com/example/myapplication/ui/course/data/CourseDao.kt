package com.example.myapplication.ui.course.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.ui.course.Course
import kotlinx.coroutines.flow.Flow
import java.util.UUID
@Dao
interface CourseDao {
    @Query("SELECT * FROM course ORDER BY start")
    fun getCourses(): Flow<List<Course>>

    @Query("SELECT * FROM course WHERE id=(:id)")
    suspend fun getCourse(id: Int): Course

    @Update
    suspend fun updateCourse(course: Course)

    @Insert
    suspend fun addCourse(course: Course)
}