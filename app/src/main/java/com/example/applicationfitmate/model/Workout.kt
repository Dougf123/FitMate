package com.example.applicationfitmate.model

data class Workout (var id: Int, var name: String, var description: String, var muscleGroupID: Int, var difficulty: String) {

    override fun toString(): String {
        return "Workout: [name: $name , description: $description ]"
    }

}