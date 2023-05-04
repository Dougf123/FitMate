package com.example.applicationfitmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.example.applicationfitmate.model.WorkoutAdapter
import com.example.applicationfitmate.model.WorkoutMenu

class MainActivityStartWorkout : AppCompatActivity() {

    lateinit var listView: ListView
    lateinit var workoutMenu: WorkoutMenu
    lateinit var nameList: ArrayList<String>
    lateinit var descList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_start_workout)

        workoutMenu = WorkoutMenu(this)
        nameList = workoutMenu.getNameList()
        descList = workoutMenu.getDescList()

        listView = findViewById(R.id.myListVu)

        val workoutAdapter = WorkoutAdapter(applicationContext, nameList, descList)
        listView!!.adapter = workoutAdapter

        listView.setOnItemClickListener {parent, _, position, _ ->
            val selectedItem = parent.getItemAtPosition(position)
            val transfer = selectedItem.toString()
            val workoutID = transfer.toInt() + 1
            val intent = Intent(this,MainActivityDoWorkout::class.java).apply {
                putExtra("WorkoutID", workoutID)
            }
            startActivity(intent)
        }

    }
}