package com.app.ifplan_leite.core.data.model.utils

import androidx.room.TypeConverter
import java.util.Date

class IFPlanConverters {
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let {Date(it)}
    }
}