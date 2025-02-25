package fr.isen.ISENSmartCompanion.isensmartcompanion.model

data class EventDto(
    val id: String,
    val title: String,
    val description: String,
    val date: String,
    val location: String,
    val category: String
)
