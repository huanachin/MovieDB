package com.example.themovieapp.data.repository.interfaces

import com.example.themovieapp.data.entity.MovieResultEntity
import com.example.themovieapp.data.response.PopularMoviesResponse
import io.reactivex.Single

interface MoviesRepository {
    fun fetchPopularMovies(): Single<PopularMoviesResponse>
    fun fetchMovieDetail(movieId: Int): Single<MovieResultEntity>
}