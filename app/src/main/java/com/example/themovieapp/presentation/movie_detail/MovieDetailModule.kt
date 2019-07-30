package com.example.themovieapp.presentation.movie_detail

import androidx.lifecycle.ViewModelProvider
import com.example.themovieapp.data.preferences.PreferencesDataStore
import com.example.themovieapp.data.repository.interfaces.MoviesRepository
import com.example.themovieapp.executor.interfaces.PostExecutionThread
import com.example.themovieapp.executor.interfaces.ThreadExecutor
import com.example.themovieapp.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class MovieDetailModule {

    @Provides
    fun providesMovieDetailViewModel(
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread,
        moviesRepository: MoviesRepository,
        preferencesDataStore: PreferencesDataStore
    ): MovieDetailViewModel {
        return MovieDetailViewModel(threadExecutor, postExecutionThread, moviesRepository, preferencesDataStore)
    }

    @Provides
    fun provideViewModelProvider(viewModel: MovieDetailViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }

}