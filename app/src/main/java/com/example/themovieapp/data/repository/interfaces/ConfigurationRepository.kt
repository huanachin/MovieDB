package com.example.themovieapp.data.repository.interfaces

import com.example.themovieapp.data.response.ConfigurationResponse
import io.reactivex.Single

interface ConfigurationRepository {
    fun fetchConfiguration(): Single<ConfigurationResponse>
}