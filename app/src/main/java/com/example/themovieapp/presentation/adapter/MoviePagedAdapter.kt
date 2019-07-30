package com.example.themovieapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovieapp.R
import com.example.themovieapp.data.entity.MovieEntity
import com.example.themovieapp.utils.Constants
import kotlinx.android.synthetic.main.row_movie.view.*


class MoviePagedAdapter constructor(private val baseImageUrl: String) :
    PagedListAdapter<MovieEntity, MoviePagedAdapter.ViewHolder>(diffCallback) {

    private lateinit var mListener: MovieListener

    interface MovieListener {
        fun onMovieSelected(movie: MovieEntity)
    }

    fun setListener(listener: MovieListener) {
        this.mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)!!
        Glide.with(holder.itemView.context)
            .load("$baseImageUrl${Constants.POSTER_SIZE}${movie.poster_path}")
            .into(holder.ivMovie)
        holder.itemView.setOnClickListener { mListener.onMovieSelected(movie) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivMovie: ImageView = view.ivMovie
    }

    companion object {
        val diffCallback: DiffUtil.ItemCallback<MovieEntity> = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return true
            }
        }
    }
}
