package com.example.applicationfitmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivityNutrition : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_nutrition)
    }

    fun workouts(view: View){
        val intent = Intent(this,MainActivityWorkouts::class.java)
        startActivity(intent)
    }

    fun progress(view: View){
        val intent = Intent(this,MainActivityProgress::class.java)
        startActivity(intent)
    }

    fun menu(view: View){
        val intent = Intent(this,MainActivityMenu::class.java)
        startActivity(intent)
    }

    fun settings(view: View){
        val intent = Intent(this,MainActivitySettings::class.java)
        startActivity(intent)
    }

}