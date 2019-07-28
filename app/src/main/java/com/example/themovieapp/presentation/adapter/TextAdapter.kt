package com.example.themovieapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.themovieapp.R
import kotlinx.android.synthetic.main.row_text.view.*

class TextAdapter : RecyclerView.Adapter<TextAdapter.ViewHolder>() {

    private var texts = ArrayList<String>()

    fun updateTexts(texts: List<String>) {
        this.texts.clear()
        this.texts.addAll(texts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_text, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return texts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val text = texts[position]
        holder.tvGenre.text = text
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvGenre: TextView = view.tvGenre
    }
}