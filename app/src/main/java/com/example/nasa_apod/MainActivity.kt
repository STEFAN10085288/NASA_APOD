package com.example.nasa_apod

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import java.net.URL
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import com.google.gson.Gson
import java.util.logging.Handler

class MainActivity : AppCompatActivity() {

    val APODDate :String = ""
    private lateinit var  searchBtn : Button
    private lateinit var  edtSearchedDate : EditText
    private lateinit var searchDate :String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchBtn = findViewById(R.id.btnGetSelectedAPODDate)
        edtSearchedDate = findViewById(R.id.edtAPODDate)


        searchBtn.setOnClickListener {
            searchDate = edtSearchedDate.text.toString()
            Log.i("SearchDate","Search date: $searchDate")
            getAPOD(searchDate)
        }

    }


    fun getAPOD(searchDate : String)
    {
        val executor = Executors.newSingleThreadExecutor()

        // Declare variables to hold the values
        var title = ""
        var date = ""
        var explanation = ""
        var imageUrl = ""

        //reads from url
        executor.execute{
            val url = URL("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&date=${searchDate}");
            val json = url.readText()

            //parse json to APOD object
            val apod = Gson().fromJson(json,APOD::class.java)

            //checks if it null before proceeding
            apod?.let {
                //assigns data to local variables
                 title = apod.title
                 date = apod.date
                 explanation = apod.explanation
                 imageUrl = apod.url

                //log data
                Log.i("APOD", "Title: $title")
                Log.i("APOD", "Date: $date")
                Log.i("APOD", "Explanation: $explanation")
                Log.i("APOD", "URL: $url")


            }

            runOnUiThread {
                Log.i("runOnUiThread 1 {\n", "URL: $url")
                findViewById<TextView>(R.id.titleTextView).text = title
                findViewById<TextView>(R.id.dateTextView).text = date
                findViewById<TextView>(R.id.explanationTextView).text = explanation

                // Load the image using the imageUrl variable
                Log.i("runOnUiThread 2 {\n", "URL: $url")
                Glide.with(this@MainActivity)
                    .load(imageUrl)
                    .into(findViewById<ImageView>(R.id.apodImageView))
            }

        }
    }
}