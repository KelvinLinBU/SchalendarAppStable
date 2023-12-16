package com.example.myapplication.ui.course.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.ui.course.Course


@Database(entities = [Course::class], version = 1)
@TypeConverters(CourseTypeConverter::class)
abstract class CourseDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
}
