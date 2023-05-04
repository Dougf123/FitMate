package com.example.applicationfitmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.applicationfitmate.model.DataBaseHelper

class MainActivityProgress : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_progress)

        val db:DataBaseHelper = DataBaseHelper(this)
        val userID = intent.getIntExtra("UserID", 0)
        val user = db.retrieveUser(userID)
        val weight = findViewById<EditText>(R.id.editTextProgressWeight)
        weight.hint = user.weight.toString()
        val age = findViewById<EditText>(R.id.editTextProgressAge)
        age.hint = user.age.toString()
    }

    fun workouts(view: View){
        val intent = Intent(this,MainActivityWorkouts::class.java)
        startActivity(intent)
    }

    fun menu(view: View){
        val intent = Intent(this,MainActivityMenu::class.java)
        startActivity(intent)
    }

    fun nutrition(view: View){
        val intent = Intent(this,MainActivityNutrition::class.java)
        startActivity(intent)
    }

    fun settings(view: View){
        val intent = Intent(this,MainActivitySettings::class.java)
        startActivity(intent)
    }

    fun update(view: View){

        val weightCheck = findViewById<EditText>(R.id.editTextProgressWeight).text.toString()
        val ageCheck = findViewById<EditText>(R.id.editTextProgressAge).text.toString()

        val db:DataBaseHelper = DataBaseHelper(this)
        val userID = intent.getIntExtra("UserID", 0)
        val user = db.retrieveUser(userID)

        if(weightCheck.isEmpty() || ageCheck.isEmpty()){
            Toast.makeText(this,"Please ensure all fields are filled in",Toast.LENGTH_SHORT).show()
        }
        else{
            val weight = findViewById<EditText>(R.id.editTextProgressWeight).text.toString().toInt()
            val age = findViewById<EditText>(R.id.editTextProgressAge).text.toString().toInt()
            user.weight = weight
            user.age = age
            val result = db.updateUser(user)
            if (result){
                Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
            }
        }

    }

}