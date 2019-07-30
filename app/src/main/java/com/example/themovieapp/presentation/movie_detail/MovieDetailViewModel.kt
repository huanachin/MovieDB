package com.example.themovieapp.presentation.movie_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.themovieapp.data.entity.MovieResultEntity
import com.example.themovieapp.data.entity.MovieVideoEntity
import com.example.themovieapp.data.entity.Resource
import com.example.themovieapp.data.preferences.PreferencesDataStore
import com.example.themovieapp.data.repository.interfaces.MoviesRepository
import com.example.themovieapp.executor.interfaces.PostExecutionThread
import com.example.themovieapp.executor.interfaces.ThreadExecutor
import com.example.themovieapp.presentation.base.BaseViewModel
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread,
    private val moviesRepository: MoviesRepository,
    private val preferencesDataStore: PreferencesDataStore
) : BaseViewModel() {

    var movieDetailResult = MutableLiveData<Resource<MovieResultEntity>>()

    init {
        fetchMovieDetail()
    }

    fun getVideos(): List<MovieVideoEntity>? {
        return movieDetailResult.value?.extractData?.videos
    }

    fun getMoviesResult(): LiveData<Resource<MovieResultEntity>> {
        return movieDetailResult
    }

    fun getBaseImageUrl(): String {
        return preferencesDataStore.getImageBaseUrl()
    }

    fun fetchMovieDetail() {
        movieDetailResult.value = Resource.Loading()
        addDisposable(
            moviesRepository.fetchMovieDetail(preferencesDataStore.getMovieId())
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribeWith(object : DisposableSingleObserver<MovieResultEntity>() {
                    override fun onSuccess(t: MovieResultEntity) {
                        movieDetailResult.value = Resource.Success(t)
                    }

                    override fun onError(e: Throwable) {
                        movieDetailResult.value = Resource.Failure()
                    }

                })
        )
    }
}