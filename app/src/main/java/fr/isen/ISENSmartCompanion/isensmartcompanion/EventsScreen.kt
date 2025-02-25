package fr.isen.ISENSmartCompanion.isensmartcompanion

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import fr.isen.ISENSmartCompanion.isensmartcompanion.model.Event

@Composable
fun EventsScreen(navController: NavController) {
    val events = listOf(
        Event(1, "Soirée BDE", "Une soirée animée par le BDE", "2025-03-01", "Campus ISEN", "Fête"),
        Event(2, "Gala annuel", "Le grand gala de fin d'année", "2025-06-15", "Salle des fêtes", "Gala"),
        Event(3, "Journée de cohésion", "Activités en plein air", "2025-04-20", "Parc National", "Cohésion")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(events) { event ->
            EventItem(event = event, onClick = {
                    navController.navigate("eventDetail/${event.id}")
            })
        }
    }
}

@Composable
fun EventItem(event: Event, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = event.title, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = event.date, fontSize = 14.sp)
            Text(text = event.location, fontSize = 14.sp)
        }
    }
}
