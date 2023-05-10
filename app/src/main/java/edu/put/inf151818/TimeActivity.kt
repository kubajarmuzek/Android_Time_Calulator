package edu.put.inf151818


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Button

class TimeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)


        val editTextHours1 = findViewById<EditText>(R.id.h1)
        val editTextMinutes1 = findViewById<EditText>(R.id.m1)
        val editTextSeconds1 = findViewById<EditText>(R.id.s1)
        val editTextHours2 = findViewById<EditText>(R.id.h2)
        val editTextMinutes2 = findViewById<EditText>(R.id.m2)
        val editTextSeconds2 = findViewById<EditText>(R.id.s2)
        val buttonAdd = findViewById<Button>(R.id.add)
        val buttonSub = findViewById<Button>(R.id.sub)
        val clear = findViewById<Button>(R.id.clear)

        clear.setOnClickListener {
            editTextHours1.setText("0")
            editTextMinutes1.setText("0")
            editTextSeconds1.setText("0")
            editTextHours2.setText("0")
            editTextMinutes2.setText("0")
            editTextSeconds2.setText("0")
        }

        buttonAdd.setOnClickListener {
            val hours1 = editTextHours1.text.toString().toIntOrNull() ?: 0
            val minutes1 = editTextMinutes1.text.toString().toIntOrNull() ?: 0
            val seconds1 = editTextSeconds1.text.toString().toIntOrNull() ?: 0

            val hours2 = editTextHours2.text.toString().toIntOrNull() ?: 0
            val minutes2 = editTextMinutes2.text.toString().toIntOrNull() ?: 0
            val seconds2 = editTextSeconds2.text.toString().toIntOrNull() ?: 0

            val totalSeconds = seconds1 + seconds2
            val carryMinutes = totalSeconds / 60
            val finalSeconds = totalSeconds % 60

            val totalMinutes = minutes1 + minutes2 + carryMinutes
            val carryHours = totalMinutes / 60
            val finalMinutes = totalMinutes % 60

            val finalHours = hours1 + hours2 + carryHours

            editTextHours1.setText(finalHours.toString())
            editTextMinutes1.setText(finalMinutes.toString())
            editTextSeconds1.setText(finalSeconds.toString())
        }

        buttonSub.setOnClickListener {
            val hours1 = editTextHours1.text.toString().toIntOrNull() ?: 0
            val minutes1 = editTextMinutes1.text.toString().toIntOrNull() ?: 0
            val seconds1 = editTextSeconds1.text.toString().toIntOrNull() ?: 0

            val hours2 = editTextHours2.text.toString().toIntOrNull() ?: 0
            val minutes2 = editTextMinutes2.text.toString().toIntOrNull() ?: 0
            val seconds2 = editTextSeconds2.text.toString().toIntOrNull() ?: 0

            val totalSeconds1 = hours1 * 3600 + minutes1 * 60 + seconds1
            val totalSeconds2 = hours2 * 3600 + minutes2 * 60 + seconds2

            val resultSeconds = totalSeconds1 - totalSeconds2

            val finalHours = resultSeconds / 3600
            val finalMinutes = (resultSeconds % 3600) / 60
            val finalSeconds = resultSeconds % 60

            editTextHours1.setText(finalHours.toString())
            editTextMinutes1.setText(finalMinutes.toString())
            editTextSeconds1.setText(finalSeconds.toString())
        }
    }
}