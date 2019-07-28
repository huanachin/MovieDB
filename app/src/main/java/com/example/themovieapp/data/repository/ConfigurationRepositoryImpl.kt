package com.example.themovieapp.data.repository

import com.example.themovieapp.data.net.RestApi
import com.example.themovieapp.data.repository.interfaces.ConfigurationRepository
import com.example.themovieapp.data.repository.interfaces.PreferenceRepository
import com.example.themovieapp.data.response.ConfigurationResponse
import com.example.themovieapp.utils.Constants
import io.reactivex.Single
import javax.inject.Inject

class ConfigurationRepositoryImpl @Inject constructor(
    private val restApi: RestApi,
    private val preferenceRepository: PreferenceRepository
) : ConfigurationRepository {

    override fun fetchConfiguration(): Single<ConfigurationResponse> {
        return restApi.fetchConfiguration(Constants.API_MOVIE_KEY).doOnSuccess {
            preferenceRepository.setImageBaseUrl(it.imageEntity?.baseUrl)
            preferenceRepository.setLogoSizes(it.imageEntity?.logoSizes)
            preferenceRepository.setPosterSizes(it.imageEntity?.posterSizes)
        }
    }

}