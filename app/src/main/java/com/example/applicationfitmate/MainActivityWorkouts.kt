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

}