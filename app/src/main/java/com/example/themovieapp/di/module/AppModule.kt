package com.example.themovieapp.di.module

import android.content.Context
import com.example.themovieapp.App
import com.example.themovieapp.data.repository.ConfigurationRepositoryImpl
import com.example.themovieapp.data.repository.MoviesRepositoryImpl
import com.example.themovieapp.data.repository.PreferenceRepositoryImpl
import com.example.themovieapp.data.repository.interfaces.ConfigurationRepository
import com.example.themovieapp.data.repository.interfaces.MoviesRepository
import com.example.themovieapp.data.repository.interfaces.PreferenceRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(app: App): Context {
        return app
    }

    @Provides
    @Singleton
    fun provideConfigurationRepository(configurationRepositoryImpl: ConfigurationRepositoryImpl): ConfigurationRepository {
        return configurationRepositoryImpl
    }

    @Provides
    @Singleton
    fun providePreferencesRepository(preferenceRepositoryImpl: PreferenceRepositoryImpl): PreferenceRepository {
        return preferenceRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(moviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository {
        return moviesRepositoryImpl
    }

}