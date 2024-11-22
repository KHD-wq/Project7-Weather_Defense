package com.example.weather_defense

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val detailView = findViewById<TextView>(R.id.detailView)
        val forecast = intent.getStringExtra("forecast")
        detailView.text = forecast ?: "No weather data available"
    }
}
