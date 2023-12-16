package com.example.myapplication.ui.course

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime
@Entity(tableName = "course")
data class Course(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val type: String,
    val code: String? = null,
    val room: String? = null,
    val date: List<LocalDate>,
    val start: LocalTime,
    val end: LocalTime
)