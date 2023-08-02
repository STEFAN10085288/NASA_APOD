package com.example.nasa_apod

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import java.net.URL
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import java.util.Calendar


//PrwdgGaA4GLTwNQAqV7egNqMioIvkAIsKQddgshk
class APODService() {
    fun getAPIDailyData(searchDate: String): APOD? {
        val url = URL("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&date=$searchDate")
        val json = url.readText()

        // Parse json to APOD object
        val apod = Gson().fromJson(json, APOD::class.java)

        return apod
    }




    @RequiresApi(Build.VERSION_CODES.O)
    fun getAPIWeeklyData() :APOD? {
        val today = LocalDate.now()

        val startOfWeek = today.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY))
        val endOfWeek = today.with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY))

        val startDate = startOfWeek.toString()
        val endDate = endOfWeek.toString()

        val url = URL("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=$startDate&end_date=$endDate")
        val json = url.readText()

        // Parse json to APOD object
        val apod = Gson().fromJson(json, APOD::class.java)

        return apod
    }
}