package com.example.themovieapp.data.repository.interfaces

interface PreferenceRepository {
    fun getImageBaseUrl(): String
    fun setImageBaseUrl(baseUrl: String?)
    fun getLogoSizes(): List<String>?
    fun setLogoSizes(logoSizes: List<String>?)
    fun getPosterSizes(): List<String>?
    fun setPosterSizes(posterSizes: List<String>?)
    fun setMovieId(movieId: Int)
    fun getMovieId(): Int
}