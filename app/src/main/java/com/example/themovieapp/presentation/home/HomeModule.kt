package com.example.themovieapp.presentation.home

import androidx.lifecycle.ViewModelProvider
import com.example.themovieapp.data.repository.interfaces.MoviesRepository
import com.example.themovieapp.data.repository.interfaces.PreferenceRepository
import com.example.themovieapp.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    fun providesHomeViewModel(
        moviesRepository: MoviesRepository,
        preferenceRepository: PreferenceRepository
    ): HomeViewModel {
        return HomeViewModel(moviesRepository, preferenceRepository)
    }

    @Provides
    fun provideViewModelProvider(viewModel: HomeViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }

}