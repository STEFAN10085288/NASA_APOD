package com.example.nasa_apod

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class helpScreen : AppCompatActivity() {

    private  lateinit var botNav:BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help_screen)


        //bot navbar
        botNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        botNav.menu.findItem(R.id.menu_Info)?.isChecked =true
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
    }
}