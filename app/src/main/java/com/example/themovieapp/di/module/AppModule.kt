package com.example.themovieapp.di.module

import android.content.Context
import com.example.themovieapp.App
import com.example.themovieapp.data.repository.ConfigurationRepositoryImpl
import com.example.themovieapp.data.repository.MoviesRepositoryImpl
import com.example.themovieapp.data.preferences.PreferencesDataStoreImpl
import com.example.themovieapp.data.repository.interfaces.ConfigurationRepository
import com.example.themovieapp.data.repository.interfaces.MoviesRepository
import com.example.themovieapp.data.preferences.PreferencesDataStore
import com.example.themovieapp.executor.JobExecutor
import com.example.themovieapp.executor.UIThread
import com.example.themovieapp.executor.interfaces.PostExecutionThread
import com.example.themovieapp.executor.interfaces.ThreadExecutor
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
    fun providePreferencesRepository(preferenceRepositoryImpl: PreferencesDataStoreImpl): PreferencesDataStore {
        return preferenceRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(moviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository {
        return moviesRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

}