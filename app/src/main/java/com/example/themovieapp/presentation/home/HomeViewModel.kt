package com.example.themovieapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.themovieapp.data.entity.MovieEntity
import com.example.themovieapp.data.entity.Resource
import com.example.themovieapp.data.repository.interfaces.MoviesRepository
import com.example.themovieapp.data.repository.interfaces.PreferenceRepository
import com.example.themovieapp.data.response.PopularMoviesResponse
import com.example.themovieapp.presentation.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val preferenceRepository: PreferenceRepository
) : BaseViewModel() {

    var moviesResult = MutableLiveData<Resource<List<MovieEntity>>>()

    fun getMoviesResult(): LiveData<Resource<List<MovieEntity>>> {
        return moviesResult
    }

    init {
        fetchPopularMovies()
    }

    fun getBaseImageUrl(): String {
        return preferenceRepository.getImageBaseUrl()
    }

    fun selectMovie(movieId: Int) {
        preferenceRepository.setMovieId(movieId)
    }

    fun fetchPopularMovies() {
        moviesResult.value = Resource.Loading()
        addDisposable(
            moviesRepository.fetchPopularMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PopularMoviesResponse>() {
                    override fun onSuccess(t: PopularMoviesResponse) {
                        moviesResult.value = Resource.Success(t.results)
                    }

                    override fun onError(e: Throwable) {
                        moviesResult.value = Resource.Failure()
                    }

                })
        )
    }
}