package com.example.sodapop

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ResultatsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecetaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultats_activity)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = RecetaAdapter(DataSource.recetas) { receta ->
            Toast.makeText(this, "Has clicat: ${receta.nombre}", Toast.LENGTH_SHORT).show()
        }

        recyclerView.adapter = adapter

        val recetasFiltradas = DataSource.recetas.filter { it.nombre.contains("s", ignoreCase = true) }

        // Actualizem el Adapter
        adapter = RecetaAdapter(recetasFiltradas) { receta ->
            Toast.makeText(this, "Has clicat: ${receta.nombre}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter

        findViewById<ImageView>(R.id.resultats_backbutton).setOnClickListener {
            startActivity(Intent(this, BuscadorActivity::class.java))
        }
    }
}