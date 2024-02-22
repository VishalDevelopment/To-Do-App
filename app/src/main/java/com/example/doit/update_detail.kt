package com.example.doit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class update_detail : AppCompatActivity() {
    lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_detail)

        database = Room.databaseBuilder(this,myDatabase::class.java,"to_do").build()

        val pos = intent.getIntExtra("id",-1)
        var text1 = findViewById<EditText>(R.id.create_title)
        var text2 = findViewById<EditText>(R.id.create_priority)

        if (pos!=-1){
           val title = dataObject.getData(pos).title
            val priority = dataObject.getData(pos).priority
            text1.setText(title)
            text2.setText(priority)

            var deleteBtn = findViewById<Button>(R.id.delete_btn)
            deleteBtn.setOnClickListener {
//                dataObject.deleteData(pos)
                GlobalScope.launch {
                    database.dao().deleteTask(Entity(pos+1,title,priority))
                }

                var intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()

            }

            var updateBtn = findViewById<Button>(R.id.update_btn)
            updateBtn.setOnClickListener {

                var NewText1= text1.text.toString()
                var NewText2 = text2.text.toString()

                // I tried to do something there
//                dataObject.updateData(pos,NewText1,NewText2)
                GlobalScope.launch {
                database.dao().updateTask(Entity(pos+1,NewText1,NewText2))
                }
                var intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
        else{

        }




    }
}