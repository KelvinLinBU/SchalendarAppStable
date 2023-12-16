package com.example.myapplication.ui.course.data

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalTime

class CourseTypeConverter {
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
    @TypeConverter
    fun fromListDate(dateList: List<LocalDate>): String {
        val stringBuilder = StringBuilder()
        for (d in dateList) {
            stringBuilder.append(d.toString()).append(",")
        }
        return stringBuilder.toString()
    }

    @TypeConverter
    fun toListDate(dateString: String): List<LocalDate> {
        val dateList = mutableListOf<LocalDate>()
        val dateStrings = dateString.split(",").filter { it.isNotEmpty() }
        for (d in dateStrings) {
            dateList.add(LocalDate.parse(d))
        }
        return dateList
    }

}