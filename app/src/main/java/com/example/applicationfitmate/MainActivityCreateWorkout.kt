package com.example.applicationfitmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.applicationfitmate.model.DataBaseHelper
import com.example.applicationfitmate.model.Workout

class MainActivityCreateWorkout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_create_workout)
    }

    fun next(view: View) {

        var name = findViewById<EditText>(R.id.editTxtCreateName).text.toString()
        var description = findViewById<EditText>(R.id.editTxtCreateDescription).text.toString()
        var muscleGroupButton = findViewById<RadioGroup>(R.id.rdGrpMuscleGroup).checkedRadioButtonId
        var difficultyButton = findViewById<RadioGroup>(R.id.rdGrpDifficulty).checkedRadioButtonId

        val db:DataBaseHelper = DataBaseHelper(this)


        if(description.isEmpty()){
            description = ""
        }

        if(muscleGroupButton == -1){
            Toast.makeText(this,"Please select a muscle Group",Toast.LENGTH_SHORT).show()
        }
        else if(difficultyButton == -1){
            Toast.makeText(this,"Please select a difficulty", Toast.LENGTH_SHORT).show()
        }
        else if (name.isEmpty()){
            Toast.makeText(this,"Please ensure you enter a name", Toast.LENGTH_SHORT).show()
        }
        else {

            var muscleGroup = findViewById<RadioButton>(muscleGroupButton).text.toString()
            var difficulty = findViewById<RadioButton>(difficultyButton).text.toString()
            var muscleGroupID = db.getMuscleGroupID(muscleGroup)

            var workout = Workout(-1, name, description, muscleGroupID, difficulty)

            val result = db.createWorkout(workout)
            if(result == -2){
                Toast.makeText(this,"Error connecting to the database",Toast.LENGTH_SHORT).show()
            }
            else if(result == -1){
                Toast.makeText(this,"Error creating workout",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Workout created successfully",Toast.LENGTH_SHORT).show()
                val workoutID = db.getWorkoutID(name)
                val intent = Intent(this,MainActivityAddExercises::class.java).apply {
                    putExtra("WorkoutID", workoutID)
                }
                startActivity(intent)
            }
        }

    }

}