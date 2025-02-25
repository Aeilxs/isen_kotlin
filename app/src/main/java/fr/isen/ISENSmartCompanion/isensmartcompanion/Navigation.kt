package fr.isen.ISENSmartCompanion.isensmartcompanion

import HomeScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = modifier.padding(innerPadding)
        ) {
            composable("events") {
                EventsScreen()
            }

            composable("home") {
                HomeScreen()
            }

            composable("history") {
                HistoryScreen()
            }
        }
    }
}
