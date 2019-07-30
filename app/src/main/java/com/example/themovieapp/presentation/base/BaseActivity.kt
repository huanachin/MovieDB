package com.example.themovieapp.presentation.base

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<T : BaseViewModel> : DaggerAppCompatActivity() {

    private var viewModel: T? = null

    abstract fun getViewModel(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel = if (viewModel == null) getViewModel() else viewModel
    }

}