package com.example.themovieapp.data.response

import com.example.themovieapp.data.entity.MovieEntity

data class PopularMoviesResponse(
    val page: String?,
    val total_results: Int?,
    val total_pages: Int?,
    val results: List<MovieEntity> = ArrayList()
)