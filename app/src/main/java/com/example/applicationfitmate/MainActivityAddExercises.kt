package com.example.applicationfitmate

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.applicationfitmate.model.CustomAdapter
import com.example.applicationfitmate.model.DataBaseHelper
import com.example.applicationfitmate.model.ExerciseMenu

class MainActivityAddExercises : AppCompatActivity() {

    lateinit var listView: ListView
    lateinit var exerciseMenu: ExerciseMenu
    lateinit var nameList: ArrayList<String>
    lateinit var instrList: ArrayList<String>
    lateinit var muscGroupList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_add_exercises)

        exerciseMenu = ExerciseMenu(this)
        nameList = exerciseMenu.getNameList()
        instrList = exerciseMenu.getInstructionList()
        muscGroupList = exerciseMenu.getMuscleGroupList()

        listView = findViewById(R.id.myListView)

        val customAdapter = CustomAdapter(applicationContext, nameList, instrList, muscGroupList)
        listView!!.adapter = customAdapter

        var workoutID = intent.getIntExtra("WorkoutID", 0)




        listView.setOnItemClickListener {parent, _, position, _ ->
            val selectedItem = parent.getItemAtPosition(position)
            val transfer = selectedItem.toString()
            val id = transfer.toInt() + 1 //db.getExercise(id) for adding exercise to workout
            val db:DataBaseHelper = DataBaseHelper(this)
            val name = db.getExerciseName(id)


            sureExercise(id)

        }



    }

    /* Code for below function retrieved from:
    *  https://www.youtube.com/watch?v=B9Iy3UXXpBg
     */
    private fun sureExercise(exerciseID: Int) {
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Add Exercise")
        builder.setMessage("Do you want to add this exercise to the workout?")
        builder.setPositiveButton("Yes", DialogInterface.OnClickListener {dialog, id ->
            //yes button selected
            //make exercise workout
            val db:DataBaseHelper = DataBaseHelper(this)
            val workoutID = intent.getIntExtra("WorkoutID", 0)
            val result = db.addExerciseToWorkout(exerciseID, workoutID)
            when(result){
                1 -> Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
                -1 -> Toast.makeText(this,"Error adding to database",Toast.LENGTH_SHORT).show()
                -2 -> Toast.makeText(this,"Error connecting to database",Toast.LENGTH_SHORT).show()
            }
            dialog.cancel()
        })
        builder.setNegativeButton("No", DialogInterface.OnClickListener{ dialog, id ->
            //no button selected
            Toast.makeText(this,"NO",Toast.LENGTH_SHORT).show()
            dialog.cancel()
        })
        var alert = builder.create()
        alert.show()

    }
}