package com.example.uirs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
    }

    override fun onStart() {
        super.onStart()
        var cv_cafe = findViewById<CardView>(R.id.cv1)
        cv_cafe.setOnClickListener{
            var i = Intent(this, Cafe_Activity::class.java)
            startActivity(i)
        }
    }
}