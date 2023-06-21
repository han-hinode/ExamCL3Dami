package com.cibertec.examcl3dami

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var signIn: Button
    lateinit var passUser: EditText
    lateinit var emailUser: EditText
    private val emailUserDefault = "victor@gmail.com"
    private val passUserDefault = "victor123"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signIn = findViewById(R.id.singIn)
        passUser = findViewById(R.id.passUser)
        emailUser = findViewById(R.id.emailUser)

        signIn.setOnClickListener{
            if (emailUser.text.isNotBlank() && passUser.text.isNotBlank() && emailUser.text.toString() == emailUserDefault && passUser.text.toString() == passUserDefault) {
                val intent = Intent(this, SearchJsonActitivity::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Incorrect email or password, Try again", Toast.LENGTH_SHORT).show()
            }
        }
    }
}