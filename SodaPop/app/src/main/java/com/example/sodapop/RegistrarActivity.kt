package com.example.sodapop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class RegistrarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)

        val editNom = findViewById<EditText>(R.id.nom_registrat)
        val btnCrear = findViewById<Button>(R.id.CrearCompte)

        btnCrear.setOnClickListener {
            val nom = editNom.text.toString()

            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("nom_registrat", nom)
            startActivity(intent)
        }
    }
}