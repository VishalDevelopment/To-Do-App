package com.example.doit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class createCard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)

        var titleBox = findViewById<EditText>(R.id.create_title)
        var priorityBox = findViewById<EditText>(R.id.create_priority)

        var saveBtn = findViewById<Button>(R.id.save_btn)

        saveBtn.setOnClickListener {

            if (titleBox.text.toString().isNotBlank() && priorityBox.text.toString().isNotBlank() ){

                var text1 = titleBox.text.toString()
                var text2 = priorityBox.text.toString()

                dataObject.setData(text1,text2)

                var intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()

            }
            else{
                Toast.makeText(this, "Blank fill aren't allowed Dear !!", Toast.LENGTH_SHORT).show()
            }

        }


    }
}