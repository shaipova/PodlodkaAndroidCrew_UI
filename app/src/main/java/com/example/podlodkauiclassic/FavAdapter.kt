package com.example.podlodkauiclassic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class FavAdapter(var list: MutableList<Session>, viewModel: SessionViewModel) : RecyclerView.Adapter<FavAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: CardView) : RecyclerView.ViewHolder(itemView) {

        val speaker: TextView = itemView.findViewById(R.id.speaker)
        val timeInterval: TextView = itemView.findViewById(R.id.time_interval)
        val date: TextView = itemView.findViewById(R.id.date)
        val description: TextView = itemView.findViewById(R.id.description)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fav_card, parent, false) as CardView
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.apply {
                speaker.text = item.speaker
                date.text = item.date
                timeInterval.text = item.timeInterval
                description.text = item.description
        }
    }

    override fun getItemCount(): Int {
        return list.size 
    }
}