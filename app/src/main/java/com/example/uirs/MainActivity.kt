package com.example.uirs

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        var btnlog = findViewById<Button>(R.id.btn_log)
        btnlog.setOnClickListener{
            val i = Intent(this, LogActivity::class.java)
            startActivity(i)
        }

        var btnsign = findViewById<Button>(R.id.btn_sign)
        btnsign.setOnClickListener {
            val i = Intent(this, SignActivity::class.java)
            startActivity(i)

        }
    }

    override fun onStart() {
        super.onStart()


    }

    override fun onResume() {
        super.onResume()
    }



}