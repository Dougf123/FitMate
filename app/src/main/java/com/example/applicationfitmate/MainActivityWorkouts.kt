package com.example.applicationfitmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivityWorkouts : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_workouts)
    }

    fun create(view: View) {
        val intent = Intent(this, MainActivityCreateWorkout::class.java)
        startActivity(intent)
    }

    fun start(view: View) {
        val intent = Intent(this, MainActivityStartWorkout::class.java)
        startActivity(intent)
    }

    fun workouts(view: View){
        val intent = Intent(this,MainActivityWorkouts::class.java)
        startActivity(intent)
    }

    fun progress(view: View){
        val intent = Intent(this,MainActivityProgress::class.java)
        startActivity(intent)
    }

    fun nutrition(view: View){
        val intent = Intent(this,MainActivityNutrition::class.java)
        startActivity(intent)
    }

    fun settings(view:View){
        val intent = Intent(this,MainActivitySettings::class.java)
        startActivity(intent)
    }

}