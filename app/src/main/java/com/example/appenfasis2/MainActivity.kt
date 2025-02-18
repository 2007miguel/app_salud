package com.example.appenfasis2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = DatabaseHelper(this)

        val btnLogin: Button = findViewById(R.id.btnLogin)
        val etIdentification: EditText = findViewById(R.id.etIdentification)
        val etPassword: EditText = findViewById(R.id.etPassword)
        val registerText: TextView = findViewById(R.id.registerText)

        btnLogin.setOnClickListener {
            val identification = etIdentification.text.toString()
            val password = etPassword.text.toString()

            if (identification.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val isValid = databaseHelper.validateUser(identification, password)
                if (isValid) {
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    // Aquí puedes redirigir a otra actividad
                } else {
                    Toast.makeText(this, "Identificación o contraseña incorrecta", Toast.LENGTH_SHORT).show()
                }
            }
        }

        registerText.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
