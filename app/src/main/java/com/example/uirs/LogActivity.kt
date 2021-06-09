package com.example.uirs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.uirs.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.regex.Matcher
import java.util.regex.Pattern

class LogActivity : AppCompatActivity() {

        fun ValidPass(pass: String): Boolean
        {
            val pattern: Pattern
            val matcher: Matcher
            val password_pattern: String
            password_pattern = "^(?=.*[A-Z])(?=.*[@_.]).*$"
            pattern = Pattern.compile(password_pattern)
            matcher = pattern.matcher(pass)

            return matcher.matches()
        }


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_log)

            val name = findViewById<EditText>(R.id.edittx_name)
            val mail = findViewById<EditText>(R.id.edittx_mail)
            val pass1 = findViewById<EditText>(R.id.edittx_pass)
            val pass2 = findViewById<EditText>(R.id.edittx_pass2)
            val btn_reg = findViewById<Button>(R.id.btn_reg)
            val string_er = findViewById<TextView>(R.id.text_forer)

            val auth = FirebaseAuth.getInstance()
            val db = FirebaseDatabase.getInstance()
            val users = db.getReference("Users")

            btn_reg.setOnClickListener{

                var k = 0
                string_er.text = ""
                //проверка всех полей
                if (name.text.toString().isBlank() || (name.text.toString().length < 2)) {
                    name.error = getString(R.string.error)
                    k++
                }

                if (mail.text.toString().isBlank()) {
                    mail.error = getString(R.string.error)
                    k++
                }

                if (pass1.text.toString().isBlank() || (!ValidPass(pass1.text.toString())) || (pass1.text.toString().length < 6) ) {
                    pass1.error = getString(R.string.error)
                    k++
                }
                if (pass2.text.toString().isBlank()) {
                    pass2.error = getString(R.string.error)
                    k++
                }

                if (pass1.text.toString() != pass2.text.toString()) {
                    string_er.text = getString(R.string.pass_pr)
                    k++
                }

                if (k == 0) {
                    auth.createUserWithEmailAndPassword(mail.text.toString(), pass1.text.toString()).addOnSuccessListener {
                        val user = User(name.text.toString(), mail.text.toString(), pass1.text.toString() )
                        FirebaseAuth.getInstance().currentUser?.let { it1 ->
                            users.child(it1.uid).setValue(user).addOnSuccessListener {
                                val i = Intent(this, SignActivity::class.java)
                                startActivity(i)
                            }
                        }
                    }
               }

            }
    }
}