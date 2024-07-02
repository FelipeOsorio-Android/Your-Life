package com.example.yourlife

import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import com.example.yourlife.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val navController by lazy {
        findNavController(R.id.fragmentContainerView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContentView(binding.root)

    }

    override fun onResume() {
        super.onResume()

        onBackPressedDispatcher.addCallback {
            val currentRoute = navController.currentDestination?.route

            when (currentRoute) {
                "homeFragScreen" -> finish()
                "resultFragScreen" -> navController.navigate(R.id.action_resultScreenFragment_to_homeScreenFragment)
            }
        }

    }

}