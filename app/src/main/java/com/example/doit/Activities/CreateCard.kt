package com.example.doit.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.doit.R
import com.example.doit.RoomDb.GetDatabase
import com.example.doit.RoomDb.ToDOEntity
import com.example.doit.RoomDb.ToDoDB
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateCard : AppCompatActivity() {
    lateinit var db: ToDoDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = GetDatabase.GetInstance(this)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_card2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var priotry = findViewById<EditText>(R.id.create_priority)
        var title = findViewById<EditText>(R.id.create_title)

        var savebtn = findViewById<Button>(R.id.save_btn)

        savebtn.setOnClickListener {
            if (priotry.text.toString().trim().isNotBlank() && title.text.toString().trim()
                    .isNotBlank()
            ) {
                GlobalScope.launch {
                    db.accessDao()
                        .InsertData(ToDOEntity(title.text.toString(), priotry.text.toString()))
                }

                var intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Blank fill not allowed", Toast.LENGTH_SHORT).show()
            }
        }

    }
}