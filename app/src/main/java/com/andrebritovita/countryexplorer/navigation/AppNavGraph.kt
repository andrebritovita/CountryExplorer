package com.andrebritovita.countryexplorer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.andrebritovita.countryexplorer.ui.details.CountryDetailScreen
import com.andrebritovita.countryexplorer.ui.list.CountryListScreen
import com.andrebritovita.countryexplorer.ui.splash.SplashScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {
        composable("splash_screen") {
            SplashScreen(onContinueClick = {
                navController.navigate("country_list") {
                    popUpTo("splash_screen") { inclusive = true }
                }
            })
        }

        composable("country_list") {
            CountryListScreen(
                onCountryClick = { countryName ->
                    navController.navigate("country_detail/$countryName")
                }
            )
        }

        composable(
            route = "country_detail/{countryName}",
            arguments = listOf(navArgument("countryName") { type = NavType.StringType })
        ) {
            CountryDetailScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}