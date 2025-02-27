package fr.isen.ISENSmartCompanion.isensmartcompanion

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun HistoryScreen() {
    val geminiService = GeminiService()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("history")
        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                val response = geminiService.generateResponse("Raconte moi une belle histoire")
                Log.d("GeminiTest", "Réponse reçue : $response")
            }
        }) {
            Text("Test Gemini")
        }
    }
}
