package ru.navapps.crimeaaero

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.navapps.crimeaaero.entities.FlightModel

class ListAdapter(private val list: List<FlightModel>)
    : RecyclerView.Adapter<FlightList>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightList {
        val inflater = LayoutInflater.from(parent.context)
        return FlightList(inflater, parent)
    }

    override fun onBindViewHolder(holder: FlightList, position: Int) {
        val flight: FlightModel = list[position]
        holder.bind(flight)
    }

    override fun getItemCount(): Int = list.size

}