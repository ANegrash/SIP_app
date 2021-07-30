package ru.navapps.crimeaaero.entities

data class FlightModel (
    val id: Int,
    val type: Int, //0 - all, 1 - departure, 2 - arrival
    val dateTime: String,
    val airlineAbbr: String,
    val airlineName: String,
    val airlineLogoPath: String,
    val flightNumber: Int,
    val flightNumberString: String,
    val checkinRow: String,
    val terminal: Int,
    val airport: String,
    val direction: String,
    val status: String,
    val delay: String,
)