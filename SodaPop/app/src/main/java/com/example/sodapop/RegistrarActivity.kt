package com.example.sodapop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
class RegistrarActivity : AppCompatActivity() {

    // ViewModel con ámbito de Activity
    private val viewModel: RegistrarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)

        // Referencias a la UI
        val editNom = findViewById<EditText>(R.id.nom_registrat)
        val editCognoms = findViewById<EditText>(R.id.cognoms)
        val editCorreu = findViewById<EditText>(R.id.correu)
        val editContrasenya = findViewById<EditText>(R.id.contrasenya)
        val editRepetir = findViewById<EditText>(R.id.repetcontrasenya)
        val btnCrear = findViewById<Button>(R.id.CrearCompte)

        // Event de l'usuari
        btnCrear.setOnClickListener {
            viewModel.validarDades(
                editNom.text.toString(),
                editCognoms.text.toString(),
                editCorreu.text.toString(),
                editContrasenya.text.toString(),
                editRepetir.text.toString()
            )
        }

        // Observer del registre correcte
        viewModel.registreCorrecte.observe(this) { correcte ->
            if (correcte) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("nom_registrat", editNom.text.toString())
                startActivity(intent)
            }
        }

        // Observer de missatges d'error
        viewModel.missatgeError.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }
}