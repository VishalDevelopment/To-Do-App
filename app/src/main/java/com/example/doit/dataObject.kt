package com.example.doit

import com.example.doit.dataObject.listData

object dataObject {

    var listData = mutableListOf<cardInfo>()

    fun setData(title: String, priority: String) {
        listData.add(cardInfo(title, priority))
    }

    fun getAllData(): List<cardInfo> {
        return listData
    }

    fun deleteAllData(){
        listData.clear()
    }
    fun getData(pos:Int):cardInfo{
        return listData[pos]
    }
    fun deleteData(pos: Int){
        listData.removeAt(pos)
    }
    fun updateData(pos:Int,title:String,priority: String){
        listData[pos].title=title
        listData[pos].priority = priority
    }

}