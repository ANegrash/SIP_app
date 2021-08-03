package ru.navapps.crimeaaero.entities

data class TypeFlight(
    val yesterday: List<FlightModel>?,
    val today: List<FlightModel>?,
    val tomorrow: List<FlightModel>?
)