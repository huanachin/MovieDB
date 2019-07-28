package com.example.themovieapp.presentation.splash

import androidx.lifecycle.ViewModelProvider
import com.example.themovieapp.data.repository.interfaces.ConfigurationRepository
import com.example.themovieapp.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class SplashModule {

    @Provides
    fun providesSplashViewModel(configurationRepository: ConfigurationRepository): SplashViewModel {
        return SplashViewModel(configurationRepository)
    }

    @Provides
    fun provideViewModelProvider(viewModel: SplashViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }

}