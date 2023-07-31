package com.example.nasa_apod

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import java.net.URL
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import com.google.gson.Gson
import java.util.logging.Handler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getAPOD()
    }




    fun getAPOD()
    {
        val executor = Executors.newSingleThreadExecutor()

        //reads from url
        executor.execute{
            val url = URL("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&date=2014-10-01");
            val json = url.readText()

            //parse json to APOD object
            val apod = Gson().fromJson(json,APOD::class.java)

            //checks if it null before proceeding
            apod?.let {
                //assigns data to local variables
                val title = apod.title
                val date = apod.date
                val explanation = apod.explanation
                val url = apod.url

                //log data
                Log.i("APOD", "Title: $title")
                Log.i("APOD", "Date: $date")
                Log.i("APOD", "Explanation: $explanation")
                Log.i("APOD", "URL: $url")

                //code to assign values to front end elements
                
            }

        }
    }
}