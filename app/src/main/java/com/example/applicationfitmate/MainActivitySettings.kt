package com.example.applicationfitmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivitySettings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_settings)
    }

    fun logOut(view: View){

        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)

    }

}