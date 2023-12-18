package com.example.app

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val title: String,
    @ColumnInfo val date: LocalDate,
    @ColumnInfo val due: LocalTime,
    @ColumnInfo val details: String? = null,
    @ColumnInfo val oneHourBefore: Boolean
)
