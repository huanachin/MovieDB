package com.example.themovieapp.data.repository

import com.example.themovieapp.data.entity.MovieDetailEntity
import com.example.themovieapp.data.entity.MovieResultEntity
import com.example.themovieapp.data.net.ApiConstants
import com.example.themovieapp.data.net.RestApi
import com.example.themovieapp.data.repository.interfaces.MoviesRepository
import com.example.themovieapp.data.response.MovieVideoResponse
import com.example.themovieapp.data.response.PopularMoviesResponse
import com.example.themovieapp.utils.Constants

import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val restApi: RestApi) : MoviesRepository {

    override fun fetchPopularMovies(): Single<PopularMoviesResponse> {
        return restApi.fetchFavoritesMovies(Constants.API_MOVIE_KEY, "en-US", 1, "")
    }

    override fun fetchMovieDetail(movieId: Int): Single<MovieResultEntity> {
        val singleDetail = restApi.fetchMovieDetail(movieId, Constants.API_MOVIE_KEY, "en-US")
        val singleVideos = restApi.fetchMovieVideos(movieId, Constants.API_MOVIE_KEY, "en-US")
        return Single.zip(
            singleDetail,
            singleVideos,
            BiFunction<MovieDetailEntity, MovieVideoResponse, MovieResultEntity> { t1: MovieDetailEntity,
                                                                                   t2: MovieVideoResponse ->
                MovieResultEntity(movieDetail = t1, videos = t2.results)
            })
    }
}