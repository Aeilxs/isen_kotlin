package fr.isen.ISENSmartCompanion.isensmartcompanion

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

object NavigationRoutes {
    const val HOME = "home"
    const val EVENTS = "events"
    const val HISTORY = "history"
}

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem(
            label = stringResource(R.string.home_screen_title),
            route = NavigationRoutes.HOME,
            icon = Icons.Default.Home
        ),
        BottomNavItem(
            label = stringResource(R.string.event_screen_title),
            route = NavigationRoutes.EVENTS,
            icon = Icons.Default.MailOutline
        ),
        BottomNavItem(
            label = stringResource(R.string.history_screen_title),
            route = NavigationRoutes.HISTORY,
            icon = Icons.Default.Refresh
        )
    )

    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.DarkGray
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

data class BottomNavItem(
    val label: String,
    val route: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)
