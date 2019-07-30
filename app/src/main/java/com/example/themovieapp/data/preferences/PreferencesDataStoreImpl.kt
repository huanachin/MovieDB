package com.example.themovieapp.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class PreferencesDataStoreImpl @Inject constructor(context: Context) :
    PreferencesDataStore {

    private val preferences: SharedPreferences
    private val gson: Gson


    init {
        preferences = context.getSharedPreferences(
            NAME,
            MODE
        )
        gson = Gson()
    }


    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    private inline fun <reified T> genericType() = object : TypeToken<T>() {}.type

    companion object {
        const val NAME = "MoviesDb"
        const val MODE = Context.MODE_PRIVATE
        const val IMAGES_BASE_URL = "IMAGES_BASE_URL"
        const val LOGO_SIZES = "LOGO_SIZES"
        const val POSTER_SIZES = "POSTER_SIZES"
        const val MOVIE_ID = "MOVIE_ID"
    }

    override fun getImageBaseUrl(): String {
        return preferences.getString(IMAGES_BASE_URL, "")!!
    }

    override fun setImageBaseUrl(baseUrl: String?) {
        preferences.edit { it.putString(IMAGES_BASE_URL, baseUrl) }
    }

    override fun getLogoSizes(): List<String>? {
        val json = preferences.getString(LOGO_SIZES, null)
        return gson.fromJson(json, genericType<List<String>>())
    }

    override fun setLogoSizes(logoSizes: List<String>?) {
        val json = gson.toJson(logoSizes)
        preferences.edit { it.putString(LOGO_SIZES, json) }
    }

    override fun getPosterSizes(): List<String>? {
        val json = preferences.getString(POSTER_SIZES, null)
        return gson.fromJson(json, genericType<List<String>>())
    }

    override fun setPosterSizes(posterSizes: List<String>?) {
        val json = gson.toJson(posterSizes)
        preferences.edit { it.putString(POSTER_SIZES, json) }
    }

    override fun setMovieId(movieId: Int) {
        preferences.edit { it.putInt(MOVIE_ID, movieId) }
    }

    override fun getMovieId(): Int {
        return preferences.getInt(MOVIE_ID, -1)
    }


}