package com.example.nasa_apod

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Calendar
import java.util.concurrent.Executors

class WeeklyAPOD : AppCompatActivity() {
    private lateinit var  botNav : BottomNavigationView
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weekly_apod)

        //bot navbar
        botNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        botNav.menu.findItem(R.id.menu_Weekly)?.isChecked =true
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



        // Set up RecyclerView and adapter
        // getting the recyclerview by its id
        val recyclerView: RecyclerView = findViewById(R.id.APOD_recyclerview)
        // this creates a vertical layout Manager
        recyclerView.layoutManager = LinearLayoutManager(this)
        // ArrayList of class ItemsViewModel
        // Setting the Adapter with the recyclerview
        val executor = Executors.newSingleThreadExecutor()

        executor.execute{
            val apodService = APODService()
            val apodList = apodService.getAPIWeeklyData()

            runOnUiThread {
                val adapter = ApodAdapter(apodList)
                recyclerView.adapter = adapter
                adapter.updateData(apodList)

            }

            }



    }

}