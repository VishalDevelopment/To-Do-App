package com.example.doit.RoomDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ToDOEntity::class],
    version = 1
)
abstract class ToDoDB() : RoomDatabase() {

    abstract fun accessDao(): ToDoDAO
}

object GetDatabase{

    var DbInstance: ToDoDB? = null
    fun GetInstance(context: Context): ToDoDB {

        if (DbInstance == null) {
            DbInstance = Room.databaseBuilder(context, ToDoDB::class.java, "ToDoDB").allowMainThreadQueries()
                    .build()
        }
        return DbInstance!!
    }

}