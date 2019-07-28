package com.example.themovieapp.di.component

import com.example.themovieapp.App
import com.example.themovieapp.di.builders.ActivityBuilder
import com.example.themovieapp.di.module.AppModule
import com.example.themovieapp.di.module.NetModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, AppModule::class,
        NetModule::class, ActivityBuilder::class]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}