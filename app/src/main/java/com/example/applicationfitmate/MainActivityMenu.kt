package com.example.applicationfitmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivityMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
    }

    fun workouts(view: View){
        val intent = Intent(this,MainActivityWorkouts::class.java)
        startActivity(intent)
    }

    fun progress(view: View){
        val userID = intent.getIntExtra("UserID", 0)
        val intent = Intent(this,MainActivityProgress::class.java).apply {
            putExtra("UserID", userID)
        }
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

    fun start(view: View){
        val difficulty = intent.getIntExtra("Difficulty", 0)
        var workoutID = 0
        when(difficulty){
            1 -> workoutID = 15
            2 -> workoutID = 16
            3 -> workoutID = 17
            4 -> workoutID = 18
        }
        val intent = Intent(this,MainActivityDoWorkout::class.java).apply {
            putExtra("WorkoutID", workoutID)
        }
        startActivity(intent)
    }

}