package com.example.themovieapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.themovieapp.data.datasource.MovieDataSourceFactory
import com.example.themovieapp.data.entity.MovieEntity
import com.example.themovieapp.data.preferences.PreferencesDataStore
import com.example.themovieapp.executor.interfaces.ThreadExecutor
import com.example.themovieapp.presentation.base.BaseViewModel
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    threadExecutor: ThreadExecutor,
    factory: MovieDataSourceFactory,
    private val preferencesDataStore: PreferencesDataStore
) : BaseViewModel() {

    var moviesPagedList: LiveData<PagedList<MovieEntity>>

    init {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(20)
            .setPageSize(20)
            .setPrefetchDistance(2)
            .build()

        moviesPagedList = LivePagedListBuilder(factory, config)
            .setFetchExecutor(threadExecutor)
            .build()
    }

    fun getBaseImageUrl(): String {
        return preferencesDataStore.getImageBaseUrl()
    }

    fun selectMovie(movieId: Int) {
        preferencesDataStore.setMovieId(movieId)
    }
}