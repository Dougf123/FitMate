package com.example.applicationfitmate.model

data class Question(val id: Int, var questionText: String) {

    override fun toString(): String {
        return "Question[ID: $id, Question: $questionText]"
    }
}