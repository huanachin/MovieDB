package com.example.themovieapp.presentation.movie_detail

import androidx.lifecycle.ViewModelProvider
import com.example.themovieapp.data.repository.interfaces.MoviesRepository
import com.example.themovieapp.data.repository.interfaces.PreferenceRepository
import com.example.themovieapp.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class MovieDetailModule {

    @Provides
    fun providesMovieDetailViewModel(
        moviesRepository: MoviesRepository,
        preferenceRepository: PreferenceRepository
    ): MovieDetailViewModel {
        return MovieDetailViewModel(moviesRepository, preferenceRepository)
    }

    @Provides
    fun provideViewModelProvider(viewModel: MovieDetailViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }

}