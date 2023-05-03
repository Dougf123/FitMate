package com.example.applicationfitmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.applicationfitmate.model.DataBaseHelper
import com.example.applicationfitmate.model.User

class MainActivityNewUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_new_user)
    }

    fun register(view: View){

        val fName = findViewById<EditText>(R.id.editTxtFName).text.toString()
        val lName = findViewById<EditText>(R.id.editTxtLName).text.toString()
        val email = findViewById<EditText>(R.id.editTxtEmail).text.toString()
        val loginName = findViewById<EditText>(R.id.editTxtRegLoginName).text.toString()
        val password = findViewById<EditText>(R.id.editTxtRegPassword).text.toString()
        val age1 = findViewById<EditText>(R.id.editTextAge).text.toString()
        val height1 = findViewById<EditText>(R.id.editTextHeight).text.toString()
        val weight1 = findViewById<EditText>(R.id.editTextWeight).text.toString()

        val age = age1.toInt()
        val height = height1.toInt()
        val weight = weight1.toInt()

        if(fName.isEmpty() || lName.isEmpty()){
            Toast.makeText(this,"Please enter a first name and last name",Toast.LENGTH_SHORT).show()
        }
        else if(email.isEmpty()){
            Toast.makeText(this,"Please enter an email",Toast.LENGTH_SHORT).show()
        }
        else if(loginName.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"Please enter a login name and password",Toast.LENGTH_SHORT).show()
        }
        else if(age1.isEmpty() || height1.isEmpty() || weight1.isEmpty()){
            Toast.makeText(this,"Please enter your age, height and weight",Toast.LENGTH_SHORT).show()
        }
        else if(age > 120 || age < 13){
            Toast.makeText(this, "Sorry but we only allow over 13 year olds, or you may have added an extra zero ;)", Toast.LENGTH_SHORT).show()
        }
        else{
            //try to add new user
            val newUser = User(-1,fName, lName, email, loginName, password, age, height, weight)
            val db = DataBaseHelper(this)
            val result = db.addUser(newUser)
            when(result){
                1 -> {
                    Toast.makeText(this,"User successfully added", Toast.LENGTH_SHORT).show()
                    var userID = db.retrieveUserID(loginName)
                    val intent = Intent(this,MainActivityQuestionaire::class.java).apply {
                        putExtra("userID", userID)
                    }
                    startActivity(intent)
                }
                -1 -> Toast.makeText(this,"Error on creating new User",Toast.LENGTH_SHORT).show()
                -2 -> Toast.makeText(this,"Error, cannot open database",Toast.LENGTH_SHORT).show()
                -3 -> Toast.makeText(this,"Error, Login Name already exists",Toast.LENGTH_SHORT).show()
            }
        }

    }

}