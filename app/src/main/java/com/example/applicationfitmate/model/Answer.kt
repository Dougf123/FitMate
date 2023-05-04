package com.example.applicationfitmate.model

data class Answer(val id: Int, var answerText: String, var questionID: Int) {

    override fun toString(): String {
        return "Answer:[ID: $id, Answer: $answerText, question: $questionID]"
    }

}