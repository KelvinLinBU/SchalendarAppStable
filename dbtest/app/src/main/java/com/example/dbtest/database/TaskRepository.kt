package com.example.dbtest.database

import androidx.annotation.WorkerThread
import androidx.room.Insert
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao,private val courseDao: CourseDao,private val buildingDao: BuildingDao) {
    val allTasks: Flow<List<Task>> = taskDao.getTasks()
    val allMCourse: Flow<List<Course>> = courseDao.getCourseM()
    val allTuCourse: Flow<List<Course>> = courseDao.getCourseTu()
    val allWCourse: Flow<List<Course>> = courseDao.getCourseW()
    val allThCourse: Flow<List<Course>> = courseDao.getCourseTh()
    val allFCourse: Flow<List<Course>> = courseDao.getCourseF()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun allBuildings() = buildingDao.getBuildings()
    suspend fun addTask(task: Task) = taskDao.addTask(task)
    suspend fun delTask(task: Task) = taskDao.delTask(task)
    suspend fun addCourse(course: Course) = courseDao.addCourse(course)
    suspend fun delCourse(course: Course) = courseDao.delCourse(course)
    //suspend fun getAddress(abb: String): Building = buildingDao.getAddress(abb)
    //suspend fun addBuilding(building: Building) = buildingDao.addBuilding(building)


}