package fr.isen.ISENSmartCompanion.isensmartcompanion

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun HistoryScreen() {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val chatDao = db.chatMessageDao()

    var messages by remember { mutableStateOf<List<ChatMessage>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            messages = chatDao.getAllMessages()
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "History", style = MaterialTheme.typography.headlineSmall)

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(messages) { message ->
                HistoryItem(message, onDelete = {
                    coroutineScope.launch {
                        chatDao.deleteMessage(message)
                        messages = chatDao.getAllMessages()
                    }
                })
            }
        }

        Button(onClick = {
            coroutineScope.launch {
                chatDao.clearHistory()
                messages = emptyList()
            }
        }) {
            Text("Clear History")
        }
    }
}

@Composable
fun HistoryItem(message: ChatMessage, onDelete: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), elevation = CardDefaults.cardElevation(4.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Q: ${message.question}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "A: ${message.response}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onDelete) {
                Text("Delete")
            }
        }
    }
}
