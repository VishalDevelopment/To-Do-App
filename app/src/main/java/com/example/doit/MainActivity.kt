package com.example.doit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/* Create a Database to create Database*/
// entity - table
// dao = queries
// database

class MainActivity : AppCompatActivity() {

    lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Room.databaseBuilder(this,myDatabase::class.java,"to_do").build()

   var recyclerView = findViewById<RecyclerView>(R.id.tasks)

        GlobalScope.launch {

        recyclerView.adapter = MyAdapter(database.dao().getTask())
        }

        recyclerView.layoutManager = LinearLayoutManager(this)

        var addbtn = findViewById<FloatingActionButton>(R.id.addButton)
        addbtn.setOnClickListener {
            val intent = Intent(this,createCard::class.java)
            startActivity(intent)
        }

        var deleteAll = findViewById<AppCompatButton>(R.id.deleteAll)
        deleteAll.setOnClickListener {
            dataObject.deleteAllData()
            GlobalScope.launch {
                database.dao().deleteAll()
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}