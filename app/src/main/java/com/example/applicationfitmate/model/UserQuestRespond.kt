package com.example.applicationfitmate.model

data class UserQuestRespond(val id: Int, var questionID: Int, var answerID: Int, var userID: Int){
    override fun toString(): String {
        return "Response:[id: $id, question: $questionID, answer: $answerID, user: $userID]"
    }
}