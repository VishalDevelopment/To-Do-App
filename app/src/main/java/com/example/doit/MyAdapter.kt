package com.example.doit

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.util.Locale

class MyAdapter(var context: Context, var dataList: List<cardInfo>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title = itemView.findViewById<TextView>(R.id.title)
        var priority = itemView.findViewById<TextView>(R.id.priority)
        var layout = itemView.findViewById<LinearLayout>(R.id.myLayout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context)
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
            val intent = Intent(context,update_detail::class.java)
            intent.putExtra("id",position)


            context.startActivity(intent)
        }
    }

}