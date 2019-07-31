package com.example.themovieapp.data.repository

import com.example.themovieapp.data.entity.MovieDetailEntity
import com.example.themovieapp.data.entity.MovieEntity
import com.example.themovieapp.data.entity.MovieResultEntity
import com.example.themovieapp.data.net.RestApi
import com.example.themovieapp.data.repository.interfaces.MoviesRepository
import com.example.themovieapp.data.response.MovieVideoResponse
import com.example.themovieapp.utils.Constants
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val restApi: RestApi) : MoviesRepository {

    override fun fetchPopularMovies(page: Int): Single<List<MovieEntity>> {
        return restApi.fetchFavoritesMovies(Constants.API_MOVIE_KEY, Constants.DEFAULT_LANGUAGE, page, "")
            .map { it.results }
    }

    override fun fetchMovieDetail(movieId: Int): Single<MovieResultEntity> {
        val singleDetail = restApi.fetchMovieDetail(movieId, Constants.API_MOVIE_KEY, Constants.DEFAULT_LANGUAGE)
        val singleVideos = restApi.fetchMovieVideos(movieId, Constants.API_MOVIE_KEY, Constants.DEFAULT_LANGUAGE)
        return Single.zip(singleVideos,
            singleDetail,
            BiFunction<MovieVideoResponse, MovieDetailEntity, MovieResultEntity> { t1: MovieVideoResponse,
                                                                                   t2: MovieDetailEntity ->
                MovieResultEntity(videos = t1.results, movieDetail = t2)
            })
    }
}
