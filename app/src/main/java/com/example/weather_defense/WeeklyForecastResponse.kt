package com.example.weather_defense

data class WeeklyForecastResponse(
    val city: City,
    val list: List<DailyForecast>
)

data class City(
    val name: String,
    val country: String
)

data class DailyForecast(
    val dt: Long,
    val temp: Temp,
    val weather: List<Weather>
)

data class Temp(
    val day: Double,
    val min: Double,
    val max: Double
)

data class Weather(
    val description: String,
    val icon: String
)
