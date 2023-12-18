package com.example.app.data

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalTime
class TaskTypeConverters {
    @TypeConverter
    fun fromDate(date: LocalDate):String{
        return date.toString()
    }
    @TypeConverter
    fun toDate(sd:String): LocalDate {
        return LocalDate.parse(sd)
    }
    @TypeConverter
    fun fromTime(date: LocalTime):String{
        return date.toString()
    }
    @TypeConverter
    fun toTime(sd:String): LocalTime {
        return LocalTime.parse(sd)
    }
}
