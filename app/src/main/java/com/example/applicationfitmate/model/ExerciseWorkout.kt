package com.example.applicationfitmate.model

data class ExerciseWorkout (var id: Int, var name: String, var instruction: String, var muscleGroupID: Int, var workoutID: Int){

    override fun toString(): String {
        return "Exercise: [name: $name , instruction: $instruction ]"
    }

}