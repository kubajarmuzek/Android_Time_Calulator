package edu.put.inf151818

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
import android.widget.TextView
import android.graphics.drawable.Drawable

import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateButton = findViewById<Button>(R.id.daty)

        dateButton.setOnClickListener {
            val intent = Intent(this, DestinationActivity::class.java)
            startActivity(intent)
        }

        val timeButton = findViewById<Button>(R.id.czas)

        timeButton.setOnClickListener {
            val intent = Intent(this, TimeActivity::class.java)
            startActivity(intent)
        }



    }
}