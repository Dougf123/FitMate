package com.example.applicationfitmate.model

data class MuscleGroup (var id: Int, var name: String) {

    override fun toString(): String {
        return "Muscle Group: [name: $name ]"
    }

}