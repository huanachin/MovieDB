package com.example.themovieapp.presentation.splash

import androidx.lifecycle.ViewModelProvider
import com.example.themovieapp.data.repository.interfaces.ConfigurationRepository
import com.example.themovieapp.executor.interfaces.PostExecutionThread
import com.example.themovieapp.executor.interfaces.ThreadExecutor
import com.example.themovieapp.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class SplashModule {

    @Provides
    fun providesSplashViewModel(
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread,
        configurationRepository: ConfigurationRepository
    ): SplashViewModel {
        return SplashViewModel(threadExecutor, postExecutionThread, configurationRepository)
    }

    @Provides
    fun provideViewModelProvider(viewModel: SplashViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }

}