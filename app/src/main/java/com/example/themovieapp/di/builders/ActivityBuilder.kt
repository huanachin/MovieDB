package com.example.themovieapp.di.builders

import com.example.themovieapp.presentation.home.HomeActivity
import com.example.themovieapp.presentation.home.HomeModule
import com.example.themovieapp.presentation.movie_detail.MovieDetailActivity
import com.example.themovieapp.presentation.movie_detail.MovieDetailModule
import com.example.themovieapp.presentation.splash.SplashActivity
import com.example.themovieapp.presentation.splash.SplashModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [SplashModule::class])
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [MovieDetailModule::class])
    abstract fun contributeMovieDetailActivity(): MovieDetailActivity
}