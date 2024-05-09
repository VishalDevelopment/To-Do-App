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

class UpdateCard : AppCompatActivity() {
    lateinit var db:ToDoDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update_card)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = GetDatabase.GetInstance(this)


        var title = findViewById<EditText>(R.id.update_title)
        var priority = findViewById<EditText>(R.id.update_priority)
        var update = findViewById<Button>(R.id.update_btn)
        var delete = findViewById<Button>(R.id.delete_btn)
        var pos = intent.getStringExtra("IDDATA")!!.toInt()
        if (pos!=null ){
            Toast.makeText(this, "$pos", Toast.LENGTH_SHORT).show()
            var values = db.accessDao().GetSepcData(pos)
            title.setText(values.title)
            priority.setText(values.priority)
        }

delete.setOnClickListener {

    GlobalScope.launch{
        db.accessDao().DelteData(pos)
    }
    Toast.makeText(this, "Data Deleted", Toast.LENGTH_SHORT).show()
    intent = Intent(this,MainActivity::class.java)
    startActivity(intent)
    finish()

}
        update.setOnClickListener {
            if (title.text.toString().trim().isNotBlank() && priority.text.toString().trim().isNotBlank()){
              GlobalScope.launch{
                  db.accessDao()
                      .UpdateData(ToDOEntity(title.text.toString(), priority.text.toString(), pos))
              }
                Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show()
                
                var intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Blank Fill not Allowed", Toast.LENGTH_SHORT).show()
            }
        }
        
        
    }
}