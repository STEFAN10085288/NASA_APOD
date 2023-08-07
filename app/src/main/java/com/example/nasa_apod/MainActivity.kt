package com.example.nasa_apod

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.net.URL
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import com.google.gson.Gson
import java.util.Calendar
import java.util.logging.Handler

class MainActivity : AppCompatActivity() {

    private lateinit var  searchBtn : Button
    private lateinit var searchDate :String
    private lateinit var  botNav : BottomNavigationView
    private lateinit var datebtn : Button
    var title = ""
    var date = ""
    var explanation = ""
    var url = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //for date selector
        //calendar
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        datebtn = findViewById(R.id.btnSelectDate)
        //button to show date picker
        datebtn.setOnClickListener {
            val dpd = DatePickerDialog(this,R.style.CustomDatePickerDialog,DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                searchDate = "${mYear}-${mMonth}-${mDay}"
                datebtn.text = searchDate
            },year,month,day)
            //show date picker dialog
            dpd.show()
        }


        //bot navbar
        botNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        botNav.menu.findItem(R.id.menu_Daily)?.isChecked =true
        botNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_Info -> {
                    startActivity(Intent(this, helpScreen::class.java))
                    true
                }
                R.id.menu_Weekly -> {
                    startActivity(Intent(this, WeeklyAPOD::class.java))
                    true
                }
                R.id.menu_Daily -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                else -> false
            }
        }




        searchBtn = findViewById(R.id.btnGetSelectedAPODDate)
        searchBtn.setOnClickListener {
            Log.i("SearchDate","Search date: $searchDate")
            getAPOD(searchDate)
        }

    }

    fun getAPOD(searchDate : String)
    {
        val executor = Executors.newSingleThreadExecutor()

        //reads from url
        executor.execute{
            /*val url = URL("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&date=${searchDate}");
            val json = url.readText()*/

            //parse json to APOD object
            val apodService = APODService()
            val apod = apodService.getAPIDailyData(searchDate)

            //checks if it null before proceeding
            apod?.let {
                //assigns data to local variables
                 title = apod.title
                 date = apod.date
                 explanation = apod.explanation
                 url = apod.url

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
                    .load(url)
                    .into(findViewById<ImageView>(R.id.apodImageView))
            }

        }
    }
}