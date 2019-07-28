package com.example.themovieapp.data.net

import com.example.themovieapp.data.entity.MovieDetailEntity
import com.example.themovieapp.data.response.ConfigurationResponse
import com.example.themovieapp.data.response.MovieVideoResponse
import com.example.themovieapp.data.response.PopularMoviesResponse

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApi {

    @GET(ApiConstants.PATH_CONFIGURATION)
    fun fetchConfiguration(
        @Query("api_key") apiKey: String
    ): Single<ConfigurationResponse>

    @GET(ApiConstants.PATH_FAVORITES_MOVIES)
    fun fetchFavoritesMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("region") region: String
    ): Single<PopularMoviesResponse>

    @GET(ApiConstants.PATH_MOVIE_DETAIL)
    fun fetchMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<MovieDetailEntity>

    @GET(ApiConstants.PATH_MOVIE_VIDEOS)
    fun fetchMovieVideos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<MovieVideoResponse>

}