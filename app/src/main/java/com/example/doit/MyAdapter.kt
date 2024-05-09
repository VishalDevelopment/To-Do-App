package com.example.doit

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.doit.Activities.UpdateCard
import com.example.doit.RoomDb.ToDOEntity
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlinx.coroutines.currentCoroutineContext
import java.util.Locale
import kotlin.coroutines.coroutineContext

class MyAdapter( var dataList: List<ToDOEntity>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title = itemView.findViewById<TextView>(R.id.title)
        var priority = itemView.findViewById<TextView>(R.id.priority)
        var layout = itemView.findViewById<LinearLayout>(R.id.myLayout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        when(dataList[position].priority.lowercase(Locale.getDefault())) {
            "high" -> holder.layout.setBackgroundColor(Color.parseColor("#FF0606"))
            "medium" -> holder.layout.setBackgroundColor(Color.parseColor("#FFD740"))
            "low" -> holder.layout.setBackgroundColor(Color.parseColor("#00FF40"))

        }

        holder.title.text = dataList[position].title
        holder.priority.text = dataList[position].priority

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context ,UpdateCard::class.java)
            intent.putExtra("IDDATA",dataList[position].id.toString())
          holder.itemView.context.startActivity(intent)
        }
    }

}