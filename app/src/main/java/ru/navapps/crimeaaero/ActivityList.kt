package ru.navapps.crimeaaero

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import ru.navapps.crimeaaero.entities.FlightModel
import java.io.IOException

class ActivityList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val url = getJsonFromApi("departure", "yesterday")

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
                    val responseString = response.body()!!.string()
                    //var json = Gson().fromJson(responseString, FlightModel::class.java)
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

    private fun getJsonFromApi(
        typeQuery: String?,
        dateQuery: String?,
        langQuery: String = "ru"
    ): String {
        var url = "https://new.sipaero.ru/json/schedule/?"
        if (typeQuery != "") url += "type=$typeQuery&"
        if (dateQuery != "") url += "date=$dateQuery&"
        url += "lang=$langQuery&"
        return url
    }
}