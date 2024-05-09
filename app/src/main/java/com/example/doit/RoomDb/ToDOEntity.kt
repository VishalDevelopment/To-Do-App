package com.example.doit.RoomDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ToDOEntity")
data class ToDOEntity(
    val  title:String,
    val priority:String,
    @PrimaryKey(autoGenerate = true)
    val id:Int=0
)
