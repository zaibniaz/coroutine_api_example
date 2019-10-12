package com.zaib.responsemodel

data class CityForeCast(
    val DailyForecasts: ArrayList<DailyForecast>,
    val Headline: Headline
)