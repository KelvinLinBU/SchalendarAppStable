package com.example.dbtest

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.dbtest.database.Building
import com.example.dbtest.database.Course
import com.example.dbtest.database.Task
import com.example.dbtest.database.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository): ViewModel() {
    val allTasks: Flow<List<Task>> = repository.allTasks
    val allMC: Flow<List<Course>> = repository.allMCourse
    val allTuC: Flow<List<Course>> = repository.allTuCourse
    val allWC: Flow<List<Course>> = repository.allWCourse
    val allThC: Flow<List<Course>> = repository.allThCourse
    val allFC: Flow<List<Course>> = repository.allFCourse
    val allBuildings: List<Building> = repository.allBuildings()
    fun allBuildings() = viewModelScope.launch {
        repository.allBuildings()
    }
    fun addTask(task: Task) = viewModelScope.launch {
        repository.addTask(task)
    }
    fun delTask(task: Task) = viewModelScope.launch {
        repository.delTask(task)
    }
    fun addCourse(course: Course) = viewModelScope.launch {
        repository.addCourse(course)
    }
    fun delCourse(course: Course) = viewModelScope.launch {
        repository.delCourse(course)
    }
    //suspend fun getAddress(abb: String): Building = repository.getAddress(abb)
    //fun addBuilding(building: Building) = viewModelScope.launch {
    //    repository.addBuilding(building)
    //}
}

class TaskViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}