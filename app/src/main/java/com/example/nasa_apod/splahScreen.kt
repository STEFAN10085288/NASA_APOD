package com.example.nasa_apod

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class splahScreen : AppCompatActivity() {

    private  val SPLASH_Delay : Long =3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splah_screen)

        showSplashScreen()
        android.os.Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },SPLASH_Delay)


    }
    fun showSplashScreen(){
        val imageView: ImageView  = findViewById(R.id.gifImageView)
        Glide.with(this).load(R.drawable.astronaut).into(imageView)
    }




}