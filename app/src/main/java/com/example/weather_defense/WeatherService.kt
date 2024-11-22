package com.example.weather_defense

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/forecast/daily")
    fun getWeeklyForecast(
        @Query("q") city: String,
        @Query("cnt") days: Int = 7,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Call<WeeklyForecastResponse>
}
