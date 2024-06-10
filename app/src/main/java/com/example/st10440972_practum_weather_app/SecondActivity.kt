package com.example.st10440972_practum_weather_app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        val day = intent.getStringExtra("day")
        val maxTemp = intent.getStringExtra("maxTemp")?.toIntOrNull()
        val minTemp = intent.getStringExtra("minTemp")?.toIntOrNull()

        if (maxTemp != null && minTemp != null) {
            val averageTemp = (maxTemp + minTemp) / 2

            val textViewRecord = findViewById<TextView>(R.id.textViewRecord)
            textViewRecord.text = "The average temperature for $day is: $averageTemp"
        } else {
            // Handle the case where maxTemp or minTemp is null or not a valid integer
            val textViewRecord = findViewById<TextView>(R.id.textViewRecord)
            textViewRecord.text = "Invalid temperature data for $day"
        }
        val btnBack = findViewById<Button>(R.id.btnBack)

        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
