package com.example.sodapop

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bienvenida = findViewById<TextView>(R.id.bienvenida)
        val username = intent.getStringExtra("nom")

        bienvenida.text = "Hola, $username!"

        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNav)

        bottomNav.setOnItemSelectedListener { item ->
            val selectedFragment: Fragment? = when (item.itemId) {
                //R.id.home_fragment -> HomeFragment()
                //R.id.dashboard_fragment -> DashboardFragment()
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