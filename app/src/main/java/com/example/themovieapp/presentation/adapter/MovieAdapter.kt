package com.example.themovieapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovieapp.R
import com.example.themovieapp.data.entity.MovieEntity
import com.example.themovieapp.utils.Constants
import kotlinx.android.synthetic.main.row_movie.view.*

class MovieAdapter constructor(private val baseImageUrl: String) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movies = ArrayList<MovieEntity>()
    private lateinit var mListener: MovieListener

    interface MovieListener {
        fun onMovieSelected(movie: MovieEntity)
    }

    fun setListener(listener: MovieListener) {
        this.mListener = listener
    }

    fun updateMovies(movies: List<MovieEntity>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_movie, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        Glide.with(holder.itemView.context)
            .load("$baseImageUrl${Constants.BACKDROP_SIZE}${movie.backdrop_path}")
            .into(holder.ivMovie)
        holder.tvTitle.text = movie.title
        holder.itemView.setOnClickListener { mListener.onMovieSelected(movie) }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivMovie: ImageView = view.ivMovie
        val tvTitle: TextView = view.tvTitle


    }

}