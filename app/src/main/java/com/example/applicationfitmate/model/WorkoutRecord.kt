package com.example.applicationfitmate.model

data class WorkoutRecord(var id: Int, var workoutID: Int, var exerciseWorkoutID: Int, var reps: Int){

    override fun toString(): String {
        return "Record: [id: $id , reps: $reps]"
    }

}