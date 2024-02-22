package com.example.doit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class splash_screen : AppCompatActivity() {
    lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        database = Room.databaseBuilder(this,myDatabase::class.java,"to_do").build()

        GlobalScope.launch {
            dataObject.listData = database.dao().getTask() as MutableList<cardInfo>
        }

        Handler(Looper.getMainLooper()).postDelayed({
          var intent = Intent(this,MainActivity::class.java)
          startActivity(intent)
          finish()
        },2000)

    }
}