package com.example.applicationfitmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.applicationfitmate.model.Answer
import com.example.applicationfitmate.model.DataBaseHelper
import com.example.applicationfitmate.model.Question
import com.example.applicationfitmate.model.UserQuestRespond

class MainActivityQuestionaire : AppCompatActivity() {

    val db: DataBaseHelper = DataBaseHelper(this)
    var questionList = ArrayList<Question>()
    var answerList = ArrayList<Answer>()

    var index = 0


    var qu1 = UserQuestRespond(-1,-1,-1,-1)
    var qu2 = UserQuestRespond(-1,-1,-1,-1)
    var qu3 = UserQuestRespond(-1,-1,-1,-1)
    var qu4 = UserQuestRespond(-1,-1,-1,-1)
    var qu5 = UserQuestRespond(-1,-1,-1,-1)
    var qu6 = UserQuestRespond(-1,-1,-1,-1)
    var qu7 = UserQuestRespond(-1,-1,-1,-1)
    var qu8 = UserQuestRespond(-1,-1,-1,-1)
    var qu9 = UserQuestRespond(-1,-1,-1,-1)
    var qu10 = UserQuestRespond(-1,-1,-1,-1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_questionaire)

        val userID = intent.getIntExtra("userID", 0)

        qu1.userID = userID
        qu2.userID = userID
        qu3.userID = userID
        qu4.userID = userID
        qu5.userID = userID
        qu6.userID = userID
        qu7.userID = userID
        qu8.userID = userID
        qu9.userID = userID
        qu10.userID = userID

        questionList = db.getQuestions()
        answerList = db.getAnswers(questionList[0].id)

        var questionText = findViewById<TextView>(R.id.txtQuestion)
        val rdBtnA = findViewById<RadioButton>(R.id.rdBtnA)
        val rdBtnB = findViewById<RadioButton>(R.id.rdBtnB)
        val rdBtnC = findViewById<RadioButton>(R.id.rdBtnC)
        val rdBtnD = findViewById<RadioButton>(R.id.rdBtnD)

        rdBtnA.text = answerList[0].answerText
        rdBtnB.text = answerList[1].answerText
        rdBtnC.text = answerList[2].answerText
        rdBtnD.text = answerList[3].answerText



        questionText.text = questionList[0].questionText

    }

    fun next(view: View){

        var questionText = findViewById<TextView>(R.id.txtQuestion)
        val rdGroup = findViewById<RadioGroup>(R.id.rdGrpQuestion)
        val rdBtnA = findViewById<RadioButton>(R.id.rdBtnA)
        val rdBtnB = findViewById<RadioButton>(R.id.rdBtnB)
        val rdBtnC = findViewById<RadioButton>(R.id.rdBtnC)
        val rdBtnD = findViewById<RadioButton>(R.id.rdBtnD)
        val btnNext = findViewById<Button>(R.id.btnNext)


        if(rdGroup.checkedRadioButtonId == -1){
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
        }else{
            //saving of the answer
            when(index){
                0 -> {
                    qu1.questionID = questionList[index].id
                    if(rdBtnA.isChecked){
                        qu1.answerID = db.retrieveAnswerID(rdBtnA.text.toString())
                    }
                    else if(rdBtnB.isChecked){
                        qu1.answerID = db.retrieveAnswerID(rdBtnB.text.toString())
                    }
                    else if(rdBtnC.isChecked){
                        qu1.answerID = db.retrieveAnswerID(rdBtnC.text.toString())
                    }
                    else if(rdBtnD.isChecked){
                        qu1.answerID = db.retrieveAnswerID(rdBtnD.text.toString())
                    }
                }
                1 -> {
                    qu2.questionID = questionList[index].id
                    if(rdBtnA.isChecked){
                        qu2.answerID = db.retrieveAnswerID(rdBtnA.text.toString())
                    }
                    else if(rdBtnB.isChecked){
                        qu2.answerID = db.retrieveAnswerID(rdBtnB.text.toString())
                    }
                    else if(rdBtnC.isChecked){
                        qu2.answerID = db.retrieveAnswerID(rdBtnC.text.toString())
                    }
                    else if(rdBtnD.isChecked){
                        qu2.answerID = db.retrieveAnswerID(rdBtnD.text.toString())
                    }
                }
                2 -> {
                    qu3.questionID = questionList[index].id
                    if(rdBtnA.isChecked){
                        qu3.answerID = db.retrieveAnswerID(rdBtnA.text.toString())
                    }
                    else if(rdBtnB.isChecked){
                        qu3.answerID = db.retrieveAnswerID(rdBtnB.text.toString())
                    }
                    else if(rdBtnC.isChecked){
                        qu3.answerID = db.retrieveAnswerID(rdBtnC.text.toString())
                    }
                    else if(rdBtnD.isChecked){
                        qu3.answerID = db.retrieveAnswerID(rdBtnD.text.toString())
                    }
                }
                3 -> {
                    qu4.questionID = questionList[index].id
                    if(rdBtnA.isChecked){
                        qu4.answerID = db.retrieveAnswerID(rdBtnA.text.toString())
                    }
                    else if(rdBtnB.isChecked){
                        qu4.answerID = db.retrieveAnswerID(rdBtnB.text.toString())
                    }
                    else if(rdBtnC.isChecked){
                        qu4.answerID = db.retrieveAnswerID(rdBtnC.text.toString())
                    }
                    else if(rdBtnD.isChecked){
                        qu4.answerID = db.retrieveAnswerID(rdBtnD.text.toString())
                    }
                }
                4 -> {
                    qu5.questionID = questionList[index].id
                    if(rdBtnA.isChecked){
                        qu5.answerID = db.retrieveAnswerID(rdBtnA.text.toString())
                    }
                    else if(rdBtnB.isChecked){
                        qu5.answerID = db.retrieveAnswerID(rdBtnB.text.toString())
                    }
                    else if(rdBtnC.isChecked){
                        qu5.answerID = db.retrieveAnswerID(rdBtnC.text.toString())
                    }
                    else if(rdBtnD.isChecked){
                        qu5.answerID = db.retrieveAnswerID(rdBtnD.text.toString())
                    }
                }
                5 -> {
                    qu6.questionID = questionList[index].id
                    if(rdBtnA.isChecked){
                        qu6.answerID = db.retrieveAnswerID(rdBtnA.text.toString())
                    }
                    else if(rdBtnB.isChecked){
                        qu6.answerID = db.retrieveAnswerID(rdBtnB.text.toString())
                    }
                    else if(rdBtnC.isChecked){
                        qu6.answerID = db.retrieveAnswerID(rdBtnC.text.toString())
                    }
                    else if(rdBtnD.isChecked){
                        qu6.answerID = db.retrieveAnswerID(rdBtnD.text.toString())
                    }
                }
                6 -> {
                    qu7.questionID = questionList[index].id
                    if(rdBtnA.isChecked){
                        qu7.answerID = db.retrieveAnswerID(rdBtnA.text.toString())
                    }
                    else if(rdBtnB.isChecked){
                        qu7.answerID = db.retrieveAnswerID(rdBtnB.text.toString())
                    }
                    else if(rdBtnC.isChecked){
                        qu7.answerID = db.retrieveAnswerID(rdBtnC.text.toString())
                    }
                    else if(rdBtnD.isChecked){
                        qu7.answerID = db.retrieveAnswerID(rdBtnD.text.toString())
                    }
                }
                7 -> {
                    qu8.questionID = questionList[index].id
                    if(rdBtnA.isChecked){
                        qu8.answerID = db.retrieveAnswerID(rdBtnA.text.toString())
                    }
                    else if(rdBtnB.isChecked){
                        qu8.answerID = db.retrieveAnswerID(rdBtnB.text.toString())
                    }
                    else if(rdBtnC.isChecked){
                        qu8.answerID = db.retrieveAnswerID(rdBtnC.text.toString())
                    }
                    else if(rdBtnD.isChecked){
                        qu8.answerID = db.retrieveAnswerID(rdBtnD.text.toString())
                    }
                }
                8 -> {
                    qu9.questionID = questionList[index].id
                    if(rdBtnA.isChecked){
                        qu9.answerID = db.retrieveAnswerID(rdBtnA.text.toString())
                    }
                    else if(rdBtnB.isChecked){
                        qu9.answerID = db.retrieveAnswerID(rdBtnB.text.toString())
                    }
                    else if(rdBtnC.isChecked){
                        qu9.answerID = db.retrieveAnswerID(rdBtnC.text.toString())
                    }
                    else if(rdBtnD.isChecked){
                        qu9.answerID = db.retrieveAnswerID(rdBtnD.text.toString())
                    }
                }
                9 -> {
                    qu10.questionID = questionList[index].id
                    if(rdBtnA.isChecked){
                        qu10.answerID = db.retrieveAnswerID(rdBtnA.text.toString())
                    }
                    else if(rdBtnB.isChecked){
                        qu10.answerID = db.retrieveAnswerID(rdBtnB.text.toString())
                    }
                    else if(rdBtnC.isChecked){
                        qu10.answerID = db.retrieveAnswerID(rdBtnC.text.toString())
                    }
                    else if(rdBtnD.isChecked){
                        qu10.answerID = db.retrieveAnswerID(rdBtnD.text.toString())
                    }
                }
            }
            if((index + 1) != questionList.size){
                index++
                questionText.text = questionList[index].questionText
                rdGroup.clearCheck()
                answerList = db.getAnswers(questionList[index].id)
                rdBtnA.text = answerList[0].answerText
                rdBtnB.text = answerList[1].answerText
                rdBtnC.text = answerList[2].answerText
                rdBtnD.text = answerList[3].answerText
            }else {
                //Questionaire finished
                val db:DataBaseHelper = DataBaseHelper(this)
                val responseList = ArrayList<UserQuestRespond>()
                val userID = intent.getIntExtra("userID", 0)
                responseList.add(qu1)
                responseList.add(qu2)
                responseList.add(qu3)
                responseList.add(qu4)
                responseList.add(qu5)
                responseList.add(qu6)
                responseList.add(qu7)
                responseList.add(qu8)
                responseList.add(qu9)
                responseList.add(qu10)
                for(r in responseList){
                    db.addResponse(r)
                }
                db.close()
                btnNext.isEnabled = false
                //suggest pre made workouts
                if(qu3.answerID == 9){
                    val intent = Intent(this, MainActivityMenu::class.java).apply {
                        putExtra("Difficulty", 1)
                        putExtra("UserID",userID)
                    }
                    startActivity(intent)
                }
                else if(qu3.answerID == 10){
                    val intent = Intent(this, MainActivityMenu::class.java).apply {
                        putExtra("Difficulty", 2)
                        putExtra("UserID",userID)
                    }
                    startActivity(intent)
                }
                else if(qu3.answerID == 11){
                    val intent = Intent(this, MainActivityMenu::class.java).apply {
                        putExtra("Difficulty", 3)
                        putExtra("UserID",userID)
                    }
                    startActivity(intent)
                }
                else if(qu3.answerID == 12){
                    val intent = Intent(this, MainActivityMenu::class.java).apply {
                        putExtra("Difficulty", 4)
                        putExtra("UserID",userID)
                    }
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this,"ERROR question 3 answer ID wrong", Toast.LENGTH_SHORT).show()
                }

            }
        }

    }

}