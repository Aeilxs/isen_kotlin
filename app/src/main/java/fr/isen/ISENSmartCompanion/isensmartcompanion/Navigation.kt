package fr.isen.ISENSmartCompanion.isensmartcompanion

import HomeScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.isen.ISENSmartCompanion.isensmartcompanion.model.EventDto

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
                EventsScreen(navController = navController)
            }
            composable("eventDetail/{eventId}") { backStackEntry ->
                val eventId = backStackEntry.arguments?.getString("eventId")?.toIntOrNull()

                // On crée une liste fictive d'événements pour récupérer les détails
                val events = listOf(
                    EventDto(1, "Soirée BDE", "Une soirée animée par le BDE", "2025-03-01", "Campus ISEN", "Fête"),
                    EventDto(2, "Gala annuel", "Le grand gala de fin d'année", "2025-06-15", "Salle des fêtes", "Gala"),
                    EventDto(3, "Journée de cohésion", "Activités en plein air pour tous", "2025-04-20", "Parc National", "Cohésion")
                )

                val event = events.find { it.id == eventId }

                if (event != null) {
                    EventDetailScreen(event = event)
                } else {
                    Text("Event not found")
                }
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
