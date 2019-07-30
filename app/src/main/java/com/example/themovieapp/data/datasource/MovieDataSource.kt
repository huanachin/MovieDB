package com.example.themovieapp.data.datasource

import androidx.paging.PageKeyedDataSource
import com.example.themovieapp.data.entity.MovieEntity
import com.example.themovieapp.data.repository.interfaces.MoviesRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

class MovieDataSource constructor(private val moviesRepository: MoviesRepository) :
    PageKeyedDataSource<Int, MovieEntity>() {

    private val disposables = CompositeDisposable()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, MovieEntity>) {
        disposables.add(
            moviesRepository.fetchPopularMovies(1)
                .subscribeWith(object : DisposableSingleObserver<List<MovieEntity>>() {
                    override fun onSuccess(t: List<MovieEntity>) {
                        callback.onResult(t, null, 2)
                    }

                    override fun onError(e: Throwable) {

                    }
                })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieEntity>) {
        disposables.add(
            moviesRepository.fetchPopularMovies(params.key)
                .subscribeWith(object : DisposableSingleObserver<List<MovieEntity>>() {
                    override fun onSuccess(t: List<MovieEntity>) {
                        callback.onResult(t, params.key + 1)
                    }

                    override fun onError(e: Throwable) {

                    }
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieEntity>) {

    }

    fun destroy() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }
}