package com.example.doit.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.doit.MyAdapter
import com.example.doit.R
import com.example.doit.RoomDb.GetDatabase
import com.example.doit.RoomDb.ToDoDB
import com.google.android.material.floatingactionbutton.FloatingActionButton

/* Create a Database to create Database*/
// entity - table
// dao = queries
// database

class MainActivity : AppCompatActivity() {

    lateinit var db: ToDoDB
    lateinit var adapter: MyAdapter
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = GetDatabase.GetInstance(this)

        recyclerView = findViewById(R.id.tasks)

        rvView()

        var addbtn = findViewById<FloatingActionButton>(R.id.addButton)
        addbtn.setOnClickListener {
            var intent = Intent(this, CreateCard::class.java)
            startActivity(intent)
            finish()
        }

        var deleteAll = findViewById<AppCompatButton>(R.id.deleteAll)
        deleteAll.setOnClickListener {
            db.accessDao().DeleteAll()
            adapter.notifyDataSetChanged()
            rvView()
        }

    }

    fun rvView() {
        adapter = MyAdapter(db.accessDao().GetAllData())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        super.onDestroy()
    }
}