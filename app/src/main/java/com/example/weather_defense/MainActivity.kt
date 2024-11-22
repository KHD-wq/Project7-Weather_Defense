package com.example.weather_defense

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: WeatherViewModel

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cityInput = findViewById<EditText>(R.id.cityInput)
        val getWeatherButton = findViewById<Button>(R.id.getWeatherButton)
        val weatherView = findViewById<TextView>(R.id.weatherView)

        val repository = WeatherRepository()
        val factory = WeatherViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(WeatherViewModel::class.java)

        getWeatherButton.setOnClickListener {
            val city = cityInput.text.toString()
            viewModel.fetchWeeklyForecast(city)
        }

        viewModel.weeklyForecast.observe(this, Observer { response ->
            if (response != null && response.list.isNotEmpty()) {
                val forecastText = response.list.joinToString(separator = "\n\n") { forecast ->
                    "Date: ${java.text.SimpleDateFormat("dd MMM yyyy").format(java.util.Date(forecast.dt * 1000))}\n" +
                            "Temperature: ${forecast.temp.day} °C (Min: ${forecast.temp.min}, Max: ${forecast.temp.max})\n" +
                            "Description: ${forecast.weather[0].description}"
                }
                weatherView.text = forecastText

                // Navigate to detail activity
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("forecast", forecastText)
                startActivity(intent)
            } else {
                weatherView.text = "No weather data available"
            }
        })
    }
}
