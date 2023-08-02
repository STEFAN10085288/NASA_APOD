package com.example.nasa_apod

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Calendar

class WeeklyAPOD : AppCompatActivity() {
    private lateinit var  botNav : BottomNavigationView
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
        val data = ArrayList<APOD>()
        // This will pass the ArrayList to our Adapter
        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(APOD("heading${i}","date${i}","explanation${i}","url${i}"))
        }
        val adapter = ApodAdapter(data)
        // Setting the Adapter with the recyclerview
        recyclerView.adapter = adapter

    }


}