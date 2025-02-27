package fr.isen.ISENSmartCompanion.isensmartcompanion

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.vertexai.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GeminiService {

    private val model = Firebase.vertexAI.generativeModel("gemini-2.0-flash")

    suspend fun generateResponse(prompt: String): String {
        Log.d("GeminiService", "Sending prompt: $prompt")
        return withContext(Dispatchers.IO) {
            try {
                val response = model.generateContent(prompt)
                val responseText = response.text ?: "No response from AI."
                Log.d("GeminiService", "Received response: $responseText")
                responseText
            } catch (e: Exception) {
                Log.e("GeminiService", "Error: ${e.message}", e)
                "Error: ${e.message}"
            }
        }
    }
}
