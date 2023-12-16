package com.example.myapplication.ui.course

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.UUID

/*
class CourseViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}
*/
class CourseViewModel(date: LocalDate) : ViewModel() {
    private val courseRepository = CourseRepository.get()
    private val _courses: MutableStateFlow<List<Course>> = MutableStateFlow(emptyList())
    val crimes: StateFlow<List<Course>>
        get() = _courses.asStateFlow()

    init {
        viewModelScope.launch {
            courseRepository.getCourseDate(date).collect{_courses.value = it}
        }
    }

    suspend fun addCourse(course: Course) {
        courseRepository.addCourse(course)
    }

}

class CourseViewModelFactory(
    private val date: LocalDate
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CourseViewModel(date) as T
    }
}