package fr.isen.ISENSmartCompanion.isensmartcompanion

import fr.isen.ISENSmartCompanion.isensmartcompanion.model.EventDto
import retrofit2.Response
import retrofit2.http.GET

interface EventApiService {
    @GET("events.json")
    suspend fun getEvents(): Response<List<EventDto>>
}
