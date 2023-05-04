package com.example.applicationfitmate.model

import android.content.Context

class WorkoutMenu(context: Context) {

    private val workoutList: ArrayList<Workout>
    private val nameList: ArrayList<String>
    private val descList: ArrayList<String>
    private val db:DataBaseHelper = DataBaseHelper(context)

    init{

        workoutList = db.getAllWorkouts()
        nameList = db.getWorkoutDetails("name")
        descList = db.getWorkoutDetails("desc")

    }

    fun getNameList(): ArrayList<String>{
        return nameList
    }

    fun getDescList(): ArrayList<String>{
        return descList
    }

}