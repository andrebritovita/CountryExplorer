package com.andrebritovita.countryexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.andrebritovita.countryexplorer.ui.theme.CountryExplorerTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.rememberNavController
import com.andrebritovita.countryexplorer.navigation.AppNavGraph


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CountryExplorerTheme {
                val navController = rememberNavController()
                AppNavGraph(navController = navController)
            }
        }
    }
}