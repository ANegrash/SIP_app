package ru.navapps.crimeaaero

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.navapps.crimeaaero.databinding.ListElementBinding
import ru.navapps.crimeaaero.entities.FlightModel

//interface FlightActionListener {
//
//    fun onItemDetails(flightModel: FlightModel)
//
//}

class ListAdapter(
    private val flights: List<FlightModel>
) : RecyclerView.Adapter<FlightList>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightList {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListElementBinding.inflate(inflater, parent, false)

        return FlightList(inflater, parent)

    }

    override fun onBindViewHolder(holder: FlightList, position: Int) {
        val flight: FlightModel = flights[position]
        holder.itemView.tag = flight
        holder.bind(flight)
    }
//
//    // обработка нажатия
//    override fun onClick(v: View) {
//        val flight = v.tag as FlightModel
//        actionListener.onItemDetails(flight)
//    }

    override fun getItemCount(): Int = flights.size
}