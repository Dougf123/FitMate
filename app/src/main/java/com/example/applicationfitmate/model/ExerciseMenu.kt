package com.example.applicationfitmate.model

import android.content.Context

class ExerciseMenu(context: Context) {

    private val exerciseList: ArrayList<Exercise>
    private val nameList: ArrayList<String>
    private val instructionList: ArrayList<String>
    private val muscleGroupList: ArrayList<String>
    private val db: DataBaseHelper = DataBaseHelper(context)

    init{
        exerciseList = db.getAllExercises()
        nameList = db.getExerciseDetails("name")
        instructionList = db.getExerciseDetails("instruction")
        muscleGroupList = db.getExerciseDetails("muscleGroup")
    }

    fun getNameList(): ArrayList<String>{
        return nameList
    }

    fun getInstructionList(): ArrayList<String>{
        return instructionList
    }

    fun getMuscleGroupList(): ArrayList<String>{
        return muscleGroupList
    }

}