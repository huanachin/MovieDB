package com.example.themovieapp.presentation.home

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.themovieapp.R
import com.example.themovieapp.data.entity.MovieEntity
import com.example.themovieapp.presentation.adapter.MoviePagedAdapter
import com.example.themovieapp.presentation.base.BaseActivity
import com.example.themovieapp.presentation.movie_detail.MovieDetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class HomeActivity : BaseActivity<HomeViewModel>(), MoviePagedAdapter.MovieListener {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var moviePagedAdapter: MoviePagedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun getViewModel(): HomeViewModel {
        homeViewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
        return homeViewModel
    }

    private fun configureRecycler() {
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            rvMovies.layoutManager = GridLayoutManager(this, 2)
        } else {
            rvMovies.layoutManager = GridLayoutManager(this, 3)
        }
        moviePagedAdapter = MoviePagedAdapter(homeViewModel.getBaseImageUrl())
        moviePagedAdapter.setListener(this)
        rvMovies.adapter = moviePagedAdapter
    }

    private fun init() {
        configureRecycler()
        homeViewModel.moviesPagedList.observe(this, Observer {
            moviePagedAdapter.submitList(it)
        })
    }

    override fun onMovieSelected(movie: MovieEntity) {
        homeViewModel.selectMovie(movie.id)
        val intent = Intent(this, MovieDetailActivity::class.java)
        startActivity(intent)
    }

}
