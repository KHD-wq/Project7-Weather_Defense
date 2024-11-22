package com.example.weather_defense

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {
    private val _weeklyForecast = MutableLiveData<WeeklyForecastResponse>()
    val weeklyForecast: LiveData<WeeklyForecastResponse> get() = _weeklyForecast

    fun fetchWeeklyForecast(city: String) {
        viewModelScope.launch {
            _weeklyForecast.value = repository.getWeeklyForecast(city).value
        }
    }
}
