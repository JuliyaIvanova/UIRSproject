package com.example.uirs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class SignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

    }

    override fun onStart() {
        super.onStart()

        val root = findViewById<ConstraintLayout>(R.id.root)
        val auth = FirebaseAuth.getInstance()
        val email = findViewById<EditText>(R.id.email_sign)
        val pass = findViewById<EditText>(R.id.pass_sign)
        var btnin = findViewById<Button>(R.id.btnIn)

        btnin.setOnClickListener{

            auth.signInWithEmailAndPassword(email.text.toString(), pass.text.toString()).addOnSuccessListener {
                val i = Intent(this, MainMenu::class.java)
                startActivity(i)
            }.addOnFailureListener{
                Snackbar.make(root, "Error", Snackbar.LENGTH_SHORT).show()
            }

        }
    }
}