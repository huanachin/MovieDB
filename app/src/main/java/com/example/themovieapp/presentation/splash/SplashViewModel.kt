package com.example.themovieapp.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.themovieapp.data.entity.Resource
import com.example.themovieapp.data.repository.interfaces.ConfigurationRepository
import com.example.themovieapp.data.response.ConfigurationResponse
import com.example.themovieapp.presentation.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val configurationRepository: ConfigurationRepository) :
    BaseViewModel() {

    var configurationResult = MutableLiveData<Resource<String>>()

    fun getConfigurationResult(): LiveData<Resource<String>> {
        return configurationResult
    }

    init {
        fetchConfigurations()
    }

    fun fetchConfigurations() {
        configurationResult.value = Resource.Loading()
        addDisposable(
            configurationRepository.fetchConfiguration()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ConfigurationResponse>() {
                    override fun onSuccess(t: ConfigurationResponse) {
                        configurationResult.value = Resource.Success("Success")
                    }

                    override fun onError(e: Throwable) {
                        configurationResult.value = Resource.Failure()
                    }

                })
        )
    }


}