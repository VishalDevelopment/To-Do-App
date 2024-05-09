package com.example.doit.RoomDb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao()
interface ToDoDAO{

    @Insert
   suspend  fun InsertData(data:ToDOEntity)
   @Update
   suspend fun UpdateData(data: ToDOEntity)


    @Query("Delete from todoentity Where id = :id")
   suspend fun DelteData(id:Int)

    @Query("Delete from todoentity")
    fun  DeleteAll()

    @Query("Select * from todoentity")
    fun GetAllData():List<ToDOEntity>


    @Query("Select * from todoentity Where id = :id")
    fun GetSepcData(id :Int):ToDOEntity

}