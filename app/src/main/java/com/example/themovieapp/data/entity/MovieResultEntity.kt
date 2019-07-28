package com.example.themovieapp.data.entity

data class MovieResultEntity(
    var movieDetail: MovieDetailEntity,
    var videos: List<MovieVideoEntity> = ArrayList()
)