package com.example.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskViewModel() : ViewModel() {
    private val taskRepository = TaskRepository.get()
    private val _tasks: MutableStateFlow<List<Task>> = MutableStateFlow(emptyList())
    val tasks: StateFlow<List<Task>>
        get() = _tasks.asStateFlow()

    init {
        viewModelScope.launch {
            taskRepository.getTasks().collect{_tasks.value = it}
        }
    }

    suspend fun addCourse(task: Task) {
        taskRepository.addTask(task)
    }

}