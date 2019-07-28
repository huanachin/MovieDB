package com.example.themovieapp.data.response

import com.example.themovieapp.data.entity.MovieVideoEntity

data class MovieVideoResponse(
    val id: String,
    val results: List<MovieVideoEntity>
)