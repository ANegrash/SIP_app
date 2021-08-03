package ru.navapps.crimeaaero.entities

data class JsonObject (
    val root: String?,
    val date: String,
    val type: String?,
    val limit: String,
    val departure: TypeFlight?,
    val arrival: TypeFlight?
)
