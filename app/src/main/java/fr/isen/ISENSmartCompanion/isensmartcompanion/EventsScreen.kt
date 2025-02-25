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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.isen.ISENSmartCompanion.isensmartcompanion.model.EventDto
import kotlinx.coroutines.launch

@Composable
fun EventsScreen() {
    var events by remember { mutableStateOf<List<EventDto>>(emptyList()) }
    var selectedEvent by remember { mutableStateOf<EventDto?>(null) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val response = RetrofitClient.instance.getEvents()
            if (response.isSuccessful) {
                events = response.body() ?: emptyList()
            }
        }
    }

    if (selectedEvent != null) {
        EventDetailScreen(event = selectedEvent!!) {
            selectedEvent = null
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(events) { event ->
                EventItem(event = event, onClick = {
                    selectedEvent = event
                })
            }
        }
    }
}


@Composable
fun EventItem(event: EventDto, onClick: () -> Unit) {
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

