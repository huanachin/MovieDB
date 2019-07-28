package com.example.themovieapp.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.themovieapp.R
import com.example.themovieapp.data.entity.Resource
import com.example.themovieapp.presentation.base.BaseActivity
import com.example.themovieapp.presentation.home.HomeActivity
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject


class SplashActivity : BaseActivity<SplashViewModel>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        init()
        events()
    }

    override fun getViewModel(): SplashViewModel {
        splashViewModel = ViewModelProviders.of(this, factory).get(SplashViewModel::class.java)
        return splashViewModel
    }

    private fun init() {
        splashViewModel.getConfigurationResult().observe(this, Observer {
            when (it) {
                is Resource.Loading -> {
                    btnRetry.visibility = View.GONE
                    progress.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    navigateToMain()
                }
                is Resource.Failure -> {
                    progress.visibility = View.GONE
                    btnRetry.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun events() {
        btnRetry.setOnClickListener { splashViewModel.fetchConfigurations() }
    }

    private fun navigateToMain() {
        startActivity(Intent(this, HomeActivity::class.java))
    }
}
