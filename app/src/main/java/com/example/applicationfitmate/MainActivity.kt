package com.example.applicationfitmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.applicationfitmate.model.DataBaseHelper
import com.example.applicationfitmate.model.User

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun login(view: View){

        val message = findViewById<TextView>(R.id.txtLoginMessage)
        val loginName = findViewById<EditText>(R.id.editTxtLoginName).text.toString()
        val password = findViewById<EditText>(R.id.editTxtPassword).text.toString()

        if(loginName.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"Please enter a login name and password",Toast.LENGTH_SHORT).show()
        }
        else{
            val db = DataBaseHelper(this)
            val result = db.getUser(User(-1,"","","",loginName,password,-1,-1,-1))
            if(result == -1){
                Toast.makeText(this,"Error, User not found, please try again",Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this,MainActivityMenu::class.java)
                startActivity(intent)
            }
        }

    }

    fun register(view: View){
        val intent = Intent(this,MainActivityNewUser::class.java)
        startActivity(intent)
    }
}