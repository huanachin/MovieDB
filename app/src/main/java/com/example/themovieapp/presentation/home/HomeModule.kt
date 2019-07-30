package com.example.themovieapp.presentation.home

import androidx.lifecycle.ViewModelProvider
import com.example.themovieapp.data.datasource.MovieDataSourceFactory
import com.example.themovieapp.data.preferences.PreferencesDataStore
import com.example.themovieapp.executor.interfaces.ThreadExecutor
import com.example.themovieapp.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    fun providesHomeViewModel(
        threadExecutor: ThreadExecutor,
        movieDataSourceFactory: MovieDataSourceFactory,
        preferencesDataStore: PreferencesDataStore
    ): HomeViewModel {
        return HomeViewModel(threadExecutor, movieDataSourceFactory, preferencesDataStore)
    }

    @Provides
    fun provideViewModelProvider(viewModel: HomeViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }

}