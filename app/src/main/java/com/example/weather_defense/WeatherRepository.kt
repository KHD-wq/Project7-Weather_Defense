package com.example.weather_defense

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository {
    private val weatherService: WeatherService = RetrofitInstance.weatherService
    private val apiKey = "ff96fd24c77162ff71e919d358a32224"

    fun getWeeklyForecast(city: String): LiveData<WeeklyForecastResponse> {
        val data = MutableLiveData<WeeklyForecastResponse>()
        weatherService.getWeeklyForecast(city, apiKey = apiKey).enqueue(object : Callback<WeeklyForecastResponse> {
            override fun onResponse(call: Call<WeeklyForecastResponse>, response: Response<WeeklyForecastResponse>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                } else {
                    Log.e("WeatherRepository", "Error: ${response.code()} ${response.message()}")
                    data.value = createEmptyForecastResponse()
                }
            }

            override fun onFailure(call: Call<WeeklyForecastResponse>, t: Throwable) {
                Log.e("WeatherRepository", "Failure: ${t.message}")
                data.value = createEmptyForecastResponse()
            }
        })
        return data
    }

    private fun createEmptyForecastResponse(): WeeklyForecastResponse {
        return WeeklyForecastResponse(
            city = City(name = "", country = ""),
            list = emptyList()
        )
    }
}
