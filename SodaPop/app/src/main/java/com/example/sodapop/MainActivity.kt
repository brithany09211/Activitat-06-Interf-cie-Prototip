package com.example.sodapop

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Cridar abans de super.onCreate() i setContentView()
        val splashScreen = installSplashScreen()

        // Opcional: Per mantenir-lo a la pantalla durant més temps (p. ex. carregar dades)
        splashScreen.setKeepOnScreenCondition {
            // Retorna 'true' per mantenir el splash screen, 'false' per amagar-lo
            // Aquí hi aniria la teva lògica de càrrega
            false
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}