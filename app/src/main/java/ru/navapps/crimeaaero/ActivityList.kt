package ru.navapps.crimeaaero

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import ru.navapps.crimeaaero.entities.FlightModel
import ru.navapps.crimeaaero.entities.JsonObject
import java.io.IOException

class ActivityList : AppCompatActivity() {

    var datesArray = arrayOf("Вчера", "Сегодня", "Завтра")
    var datesArrayQuery = arrayOf("yesterday", "today", "tomorrow")
    var dateVar = 0

    var typesArray = arrayOf("Вылет", "Прилёт")
    var typesArrayQuery = arrayOf("departure", "arrival")
    var typeVar = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val spinnerDates = findViewById<View>(R.id.spinner_dates) as Spinner
        val adapterDates: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datesArray)
        adapterDates.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDates.adapter = adapterDates

        val spinnerTypes = findViewById<View>(R.id.spinner_flightTypes) as Spinner
        val adapterTypes: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typesArray)
        adapterTypes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTypes.adapter = adapterTypes

        val reloadBtn = findViewById<View>(R.id.btn_reloadTable) as ImageButton

        reloadBtn.setOnClickListener {
            setRecyclerViewContent(typesArrayQuery[typeVar], datesArrayQuery[dateVar])
        }

        val itemSelectedListenerDates: AdapterView.OnItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    dateVar = position
                    setRecyclerViewContent(typesArrayQuery[typeVar], datesArrayQuery[dateVar])
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        spinnerDates.onItemSelectedListener = itemSelectedListenerDates

        val itemSelectedListenerTypes: AdapterView.OnItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    typeVar = position
                    setRecyclerViewContent(typesArrayQuery[typeVar], datesArrayQuery[dateVar])
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        spinnerTypes.onItemSelectedListener = itemSelectedListenerTypes

        setRecyclerViewContent("departure", "tomorrow")

    }

    private fun setRecyclerViewContent(
        type : String,
        date : String,
        lang: String = "ru") {

        var url = "https://new.sipaero.ru/json/schedule/?"
        if (type != "") url += "type=$type&"
        if (date != "") url += "date=$date&"
        url += "lang=$lang&"

        val test = Get()

        test.run(
            url,
            object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    this@ActivityList.runOnUiThread(Runnable {
                        Toast.makeText(
                            applicationContext,
                            "Проблемы с соединением. Попробуйте позже",
                            Toast.LENGTH_LONG
                        ).show()
                    })
                }

                @Throws(IOException::class)
                override fun onResponse(call: Call, response: Response) {
                    val listOfObjects: ArrayList<FlightModel> = arrayListOf()
                    listOfObjects.clear()
                    val responseString = response.body()!!.string()
                    val json : JsonObject? = Gson().fromJson(responseString, JsonObject::class.java)

                    if (json != null) {
                        if (type == "departure"){
                            when (date) {
                                "yesterday" -> {
                                    for (i in json.departure?.yesterday!!)
                                        listOfObjects.add(i)
                                }
                                "today" -> {
                                    for (i in json.departure?.today!!)
                                        listOfObjects.add(i)
                                }
                                "tomorrow" -> {
                                    for (i in json.departure?.tomorrow!!)
                                        listOfObjects.add(i)
                                }
                            }
                        } else if (type == "arrival"){
                            when (date) {
                                "yesterday" -> {
                                    for (i in json.arrival?.yesterday!!)
                                        listOfObjects.add(i)
                                }
                                "today" -> {
                                    for (i in json.arrival?.today!!)
                                        listOfObjects.add(i)
                                }
                                "tomorrow" -> {
                                    for (i in json.arrival?.tomorrow!!)
                                        listOfObjects.add(i)
                                }
                            }
                        }
                    }

                    for (k in listOfObjects){
                        k.dateTime = getTimeFromDate(k.dateTime)
                    }
                    this@ActivityList.runOnUiThread(Runnable() {
                        run() {
                            val recyclerView: RecyclerView =
                                findViewById<RecyclerView>(R.id.recyclerView_flightList)
                            recyclerView.apply {
                                layoutManager = LinearLayoutManager(this@ActivityList)
                                adapter = ListAdapter(listOfObjects)
                            }
                        }
                    })
                }
            }
        )
    }


    private fun getTimeFromDate(date: String): String {
        return ((date.split("T").toTypedArray()[1]).split("+").toTypedArray()[0]).split(":")
            .toTypedArray()[0] + ":" + ((date.split("T").toTypedArray()[1]).split("+")
            .toTypedArray()[0]).split(":").toTypedArray()[1]
    }

    // обработка нажатия на item recyclerview
    private fun onListItemClick(position: Int) {
        val intent = Intent(this, ItemActivity::class.java).apply {
        }
        startActivity(intent)
    }
}