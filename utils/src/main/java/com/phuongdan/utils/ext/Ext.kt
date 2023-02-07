package com.danphuong.utils.ext

import java.text.SimpleDateFormat
import java.util.*

fun String.convertToMilisecond(timeFormat: String) : Long {
    val formatter = SimpleDateFormat(timeFormat)
    val date : Date = formatter.parse(this) as Date
    return date.getTime()
}

fun String.convertToDate(timeFormat: String) : Date {
    val formatter = SimpleDateFormat(timeFormat)
    return formatter.parse(this) as Date
}

fun Long.toDateTime(timeFormat : String) : String? {
    return SimpleDateFormat(timeFormat).format(this);
}