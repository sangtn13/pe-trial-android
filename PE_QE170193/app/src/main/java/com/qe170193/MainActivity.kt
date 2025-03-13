package com.qe170193

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.qe170193.data.seed.LandSeeder
import com.qe170193.ui.theme.PE_QE170193Theme
import com.qe170193.ui.viewmodel.LandViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            PE_QE170193Theme {
                LandSeeder.seed(this)

                val viewModel = remember { LandViewModel(this) }
                val navController = rememberNavController()

                LandNavHost(navController = navController, viewModel = viewModel)
            }
        }
    }
}
