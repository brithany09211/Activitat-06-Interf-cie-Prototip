package com.example.sodapop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //Mensaje de bienvenida
        val bienvenida = findViewById<TextView>(R.id.bienvenida)
        val username = intent.getStringExtra("nom_registrat")
        val mensaje = if (username.isNullOrBlank()) {
            "Hola!"
        } else {
            "Hola, $username!"
        }
        bienvenida.text = mensaje

        //Botones de buscador y filtro
        findViewById<Button>(R.id.btn_buscador).setOnClickListener {
            startActivity(Intent(this, BuscadorActivity::class.java))
        }

        //findViewById<Button>(R.id.btn_filtratge).setOnClickListener {
        //    startActivity(intent(this, FiltratgeActivity::class.java))
        //}
        //Menu navigation
        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNav)

        bottomNav.setOnItemSelectedListener { item ->
            val selectedFragment: Fragment? = when (item.itemId) {
                //R.id.nav_lesmevesreceptes -> LesmevesReceptesFragment()
                //R.id.na_perfil -> PerfilFragment()
                R.id.nav_rebost -> FragmentRebost()
                else -> null
            }

            // Realitza la transacció de Fragment manualment
            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit()
            }
            true // Indica que la selecció ha estat gestionada
        }
    }

}