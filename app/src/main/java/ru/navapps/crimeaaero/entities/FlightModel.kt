package ru.navapps.crimeaaero.entities

data class FlightModel (
    val type: String,
    var dateTime: String,
    val airline: String,
    val airlineName: String,
    val airlineLogo: String,
    val number: Int,
    val flight: String,
    val checkin: String,
    val terminal: Int,
    val airport: String,
    val direction: String,
    val status: String,
    val delay: String,
)