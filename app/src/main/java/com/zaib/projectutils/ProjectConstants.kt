package com.zaib.projectutils

import java.util.*
import java.text.SimpleDateFormat


class ProjectConstants {

    companion object {
        val appId: String = "kiWkGa8Brsh2frbExMg1oKrAAoIbO3Ef"


        // convert milliseconds into the day of the week string
        fun dayStringFormat(msecs: Long): String {
            try {
                val calendar = Calendar.getInstance()
                val tz = TimeZone.getDefault()
                calendar.timeInMillis = msecs * 1000
                calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.timeInMillis))
                val sdf = SimpleDateFormat("EEE")
                val currenTimeZone = calendar.time as Date
                return sdf.format(currenTimeZone)
            } catch (e: Exception) {
            }

            return ""

        }
    }
}