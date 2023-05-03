package com.example.applicationfitmate.model

data class Exercise (var id: Int, var name: String, var instruction: String, var muscleGroupID: Int){

    override fun toString(): String {
        return "Exercise: [name: $name , instruction: $instruction ]"
    }

}