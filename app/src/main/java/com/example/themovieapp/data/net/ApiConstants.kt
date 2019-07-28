package com.example.themovieapp.data.net

class ApiConstants {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val PATH_CONFIGURATION = "configuration"
        const val PATH_FAVORITES_MOVIES = "movie/popular"
        const val PATH_MOVIE_DETAIL = "movie/{movie_id}"
        const val PATH_MOVIE_VIDEOS = "movie/{movie_id}/videos"
    }
}