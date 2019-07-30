package com.example.themovieapp.data.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.themovieapp.data.entity.MovieEntity
import com.example.themovieapp.data.repository.interfaces.MoviesRepository
import javax.inject.Inject

class MovieDataSourceFactory @Inject constructor(private val moviesRepository: MoviesRepository) :
    DataSource.Factory<Int, MovieEntity>() {

    private lateinit var movieDataSource: MovieDataSource
    private val mutableLiveData: MutableLiveData<MovieDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, MovieEntity> {
        movieDataSource = MovieDataSource(moviesRepository)
        mutableLiveData.postValue(movieDataSource)
        return movieDataSource
    }
}