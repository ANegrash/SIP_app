package ru.navapps.crimeaaero

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

data class FlightModel (val id: Int){
    var type: Int = -1 //0 - all, 1 - departure, 2 - arrival
    @RequiresApi(Build.VERSION_CODES.O)
    var dateTime: LocalDate = LocalDate.of(0,0,0)
    var airlineAbbr: String = ""
    var airlineName: String = ""
    var airlineLogoPath: String = ""
    var flightNumber: Int = -1
    var flightNumberString: String = ""
    var checkinRow: String = ""
    var terminal: Int = -1
    var gate: String = ""
    var airport: String = ""
    var direction: String = ""
    var status: String = ""
    var delay: String = ""
}