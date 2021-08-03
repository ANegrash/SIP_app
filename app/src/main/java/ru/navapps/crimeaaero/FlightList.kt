package ru.navapps.crimeaaero

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.navapps.crimeaaero.entities.FlightModel

class FlightList(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_element, parent, false)) {
    private var timeTextView: TextView? = null
    private var cityTextViev: TextView? = null
    private var statusTextViev: TextView? = null
    private var numberTextViev: TextView? = null


    init {
        timeTextView = itemView.findViewById(R.id.textView_listTime)
        cityTextViev = itemView.findViewById(R.id.textView_listCity)
        statusTextViev = itemView.findViewById(R.id.textView_listStatus)
        numberTextViev = itemView.findViewById(R.id.textView_listFlightNumber)
    }

    fun bind(flight: FlightModel) {
        timeTextView?.text = flight.dateTime
        cityTextViev?.text = flight.direction
        statusTextViev?.text = flight.status
        numberTextViev?.text = flight.flight
    }
}