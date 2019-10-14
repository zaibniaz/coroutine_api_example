package com.zaib.responsemodel

data class DailyForecast(
    val Date: String,
    val Day: Day,
    val EpochDate: Long,
    val Link: String,
    val MobileLink: String,
    val Night: Night,
    val Sources: List<String>,
    val Temperature: Temperature
)