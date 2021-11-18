package com.shekhar.kotlin.data.local.db

import androidx.room.TypeConverter
import java.util.*

class Converter {

    @TypeConverter
    fun fromTimeStamp(value:Long?) = value?.let { Date(it) }

    @TypeConverter
    fun fromDate(date: Date?) = date?.time
}