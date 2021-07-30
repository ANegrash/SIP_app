package ru.navapps.crimeaaero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ActivityList : AppCompatActivity() {
    private val listOfObjects = listOf(
        FlightModel(1, 1, getTimeFromDate("2021-07-30T00:05:00+03:00"), "SU", "Аэрофлот", "img/img.png", 6900, "SU-6900","", 1, "", "Санкт-Петербург", "Вылетел", "")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val recyclerView : RecyclerView = findViewById<RecyclerView>(R.id.recyclerView_flightList)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ActivityList)
            adapter = ListAdapter(listOfObjects)
        }
    }

    private fun getTimeFromDate(date: String): String {
        return ((date.split("T").toTypedArray()[1]).split("+").toTypedArray()[0]).split(":").toTypedArray()[0] + ":" + ((date.split("T").toTypedArray()[1]).split("+").toTypedArray()[0]).split(":").toTypedArray()[1]
    }
}