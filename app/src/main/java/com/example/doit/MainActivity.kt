package com.example.doit

import android.content.Intent
import android.icu.lang.UCharacter.VerticalOrientation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var dataList:List<cardInfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

   var recyclerView = findViewById<RecyclerView>(R.id.tasks)
        recyclerView.adapter = MyAdapter(this,dataObject.getAllData())

        recyclerView.layoutManager = LinearLayoutManager(this)

        var addbtn = findViewById<Button>(R.id.addButton)
        addbtn.setOnClickListener {
            val intent = Intent(this,createCard::class.java)
            startActivity(intent)
        }

        var deleteAll = findViewById<AppCompatButton>(R.id.deleteAll)

        deleteAll.setOnClickListener {
            dataObject.deleteAllData()
        }

    }
}