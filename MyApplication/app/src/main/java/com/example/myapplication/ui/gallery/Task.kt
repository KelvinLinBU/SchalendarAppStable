package com.example.myapplication.ui.gallery

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val date: LocalDate,
    val due: LocalTime,
    val details: String? = null,
    val oneHourBefore: Boolean
)
