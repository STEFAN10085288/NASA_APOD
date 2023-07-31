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

        executor.execute{
            val url = URL("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&date=2014-10-01");
            val json = url.readText()


            val apodList = Gson().fromJson(json,Array<APOD>::class.java).toList()
            android.os.Handler(Looper.getMainLooper()).post{
             Log.i("APOD", "Plain json vars  $json")
                Log.i("APOD", "Converted json   ${apodList.toString()}")
            }

        }


    }
}