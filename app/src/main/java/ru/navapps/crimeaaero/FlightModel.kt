package ru.navapps.crimeaaero

import java.util.*

class FlightModel (val id: Int){
    var type: Int? = null //0 - all, 1 - departure, 2 - arrival
    var dateTime: Date? = null
    var airlineAbbr: String? = null
    var airlineName: String? = null
    var airlineLogoPath: String? = null
    var flightNumber: Int? = null
    var flightNumberString: String? = null
    var checkinRow: String? = null
    var terminal: Int? = null
    var gate: String? = null
    var airport: String? = null
    var direction: String? = null
    var status: String? = null
    var delay: String? = null
}