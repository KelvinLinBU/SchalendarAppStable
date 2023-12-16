package com.example.myapplication.ui.course

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ListItemCourseBinding
import java.util.UUID


class CourseHolder(
    private val binding: ListItemCourseBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(course: Course, onCourseClicked: (courseId:Int) -> Unit) {
        binding.cStart.text = course.start.toString()
        binding.cTitle.text = course.name
        binding.cType.text = course.type
        binding.cEnd.text = course.end.toString()
        val address =  course.code +" "+ course.room
        binding.cAddress.text = address

        binding.root.setOnClickListener {
            onCourseClicked(course.id)
        }
    }
}

class CourseAdapter(
    private val courses: List<Course>,
    private val onCourseClicked: (courseId: Int) -> Unit
) : RecyclerView.Adapter<CourseHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
        ): CourseHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListItemCourseBinding.inflate(inflater, parent, false)
            return CourseHolder(binding)
        }
    override fun onBindViewHolder(holder: CourseHolder, position: Int) {
        val course = courses[position]
        holder.bind(course, onCourseClicked)
    }

    override fun getItemCount() = courses.size

}