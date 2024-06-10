package com.example.st10440972_practum_weather_app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val edtTextDay = findViewById<EditText>(R.id.edtTextDay)
        val edtTextMaxTemp = findViewById<EditText>(R.id.edtTextMaxTemp)
        val edtTextMinTemp = findViewById<EditText>(R.id.edtTextMinTemp)
        val edtTextCondition = findViewById<EditText>(R.id.edtTextCondition)
        val btnProceed = findViewById<Button>(R.id.btnProceed)

        // Set input type for temperature EditTexts
        edtTextMaxTemp.inputType = InputType.TYPE_CLASS_NUMBER
        edtTextMinTemp.inputType = InputType.TYPE_CLASS_NUMBER

        btnProceed.setOnClickListener {
            val day = edtTextDay.text.toString()
            val maxTempStr = edtTextMaxTemp.text.toString()
            val minTempStr = edtTextMinTemp.text.toString()
            val condition = edtTextCondition.text.toString()

            if (maxTempStr.isNotEmpty() && minTempStr.isNotEmpty()) {
                try {
                    maxTempStr.toInt()
                    minTempStr.toInt()

                    val intent = Intent(this, SecondActivity::class.java)
                    intent.putExtra("day", day)
                    intent.putExtra("maxTemp", maxTempStr)
                    intent.putExtra("minTemp", minTempStr)
                    intent.putExtra("condition", condition)
                    startActivity(intent)
                } catch (e: NumberFormatException) {
                    // Handle the exception if input is not a valid integer
                    edtTextMaxTemp.error = "Please enter a valid number"
                    edtTextMinTemp.error = "Please enter a valid number"
                }
            } else {
                if (maxTempStr.isEmpty()) {
                    edtTextMaxTemp.error = "This field is required"
                }
                if (minTempStr.isEmpty()) {
                    edtTextMinTemp.error = "This field is required"
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

class InputType {
    companion object {
        val TYPE_CLASS_NUMBER: Int
            get() {
                TODO()
            }
    }

}
