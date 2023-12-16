package com.example.myapplication.ui.course

import android.content.Context
import androidx.room.Room
import com.example.myapplication.ui.course.data.CourseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.time.LocalDate


private const val DATABASE_NAME = "course-database"
class CourseRepository private constructor(
    context: Context,
    private val coroutineScope: CoroutineScope = GlobalScope
){
    private val database: CourseDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            CourseDatabase::class.java,
            DATABASE_NAME
        ).build()

    private fun getCourses(): Flow<List<Course>> = database.courseDao().getCourses()
    private fun isDate(date: LocalDate,ld :List<LocalDate>): Boolean{
        for(d in ld){
            if(d==date){
                return true
            }
        }
        return false
    }
    fun getCourseDate(date:LocalDate): Flow<List<Course>> {
        return getCourses().map { courses ->
            courses.filter { course -> isDate(date, course.date)}}
    }
    suspend fun getCourse(id:Int): Course = database.courseDao().getCourse(id)
    fun updateCourse(course: Course) {
        coroutineScope.launch { database.courseDao().updateCourse(course) }
    }
    suspend fun addCourse(course: Course) { database.courseDao().addCourse(course) }

    companion object {
        private var INSTANCE: CourseRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CourseRepository(context)
            }
        }

        fun get(): CourseRepository {
            return INSTANCE
                ?: throw IllegalStateException("CourseRepository must be initialized")
        }
    }
}

