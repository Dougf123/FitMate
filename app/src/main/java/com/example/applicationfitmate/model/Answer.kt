package com.example.applicationfitmate.model

data class Answer(val id: Int, var answerText: String) {

    override fun toString(): String {
        return "Answer:[ID: $id, Answer: $answerText]"
    }

}