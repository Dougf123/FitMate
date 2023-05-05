package com.example.applicationfitmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.applicationfitmate.model.DataBaseHelper
import com.example.applicationfitmate.model.ExerciseWorkout
import com.example.applicationfitmate.model.WorkoutRecord

class MainActivityDoWorkout : AppCompatActivity() {

    val db: DataBaseHelper = DataBaseHelper(this)

    var exerciseList = ArrayList<ExerciseWorkout>()

    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_do_workout)

        val workoutID = intent.getIntExtra("WorkoutID", 0)

        var name = findViewById<TextView>(R.id.txtExWorName)
        var instr = findViewById<TextView>(R.id.txtExWorInstr)

        exerciseList = db.getAllExerciseWorkouts(workoutID)

        name.text = exerciseList[0].name
        instr.text = exerciseList[0].instruction

    }

    fun next(view: View){
        var name = findViewById<TextView>(R.id.txtExWorName)
        var instr = findViewById<TextView>(R.id.txtExWorInstr)
        var repsCheck = findViewById<EditText>(R.id.editTextReps).text.toString()
        val workoutID = intent.getIntExtra("WorkoutID", 0)
        val db:DataBaseHelper = DataBaseHelper(this)
        if(repsCheck.isEmpty()){
            Toast.makeText(this,"Please enter the number of reps you managed",Toast.LENGTH_SHORT).show()
        }
        else{
            var reps = findViewById<EditText>(R.id.editTextReps).text.toString().toInt()
            var record = WorkoutRecord(-1,workoutID,exerciseList[index].id,reps)
            val result = db.addRecord(record)
            when(result){
                1 -> {
                    Toast.makeText(this, "Success",Toast.LENGTH_SHORT).show()
                    if((index + 1) != exerciseList.size){
                        index++
                        name.text = exerciseList[index].name
                        instr.text = exerciseList[index].instruction
                    }
                    else{
                        Toast.makeText(this,"FINISHED",Toast.LENGTH_SHORT).show()
                        val intent = Intent(this,MainActivityMenu::class.java)
                        startActivity(intent)
                    }
                }
                -1 -> Toast.makeText(this,"Error adding record to database", Toast.LENGTH_SHORT).show()
                -2 -> Toast.makeText(this,"Error connecting to database",Toast.LENGTH_SHORT).show()
            }

        }



    }

}