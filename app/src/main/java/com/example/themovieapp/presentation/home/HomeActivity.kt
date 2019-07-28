package com.example.themovieapp.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.themovieapp.R
import com.example.themovieapp.data.entity.MovieEntity
import com.example.themovieapp.data.entity.Resource
import com.example.themovieapp.presentation.adapter.MovieAdapter
import com.example.themovieapp.presentation.base.BaseActivity
import com.example.themovieapp.presentation.movie_detail.MovieDetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HomeActivity : BaseActivity<HomeViewModel>(), MovieAdapter.MovieListener {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        events()
    }

    override fun getViewModel(): HomeViewModel {
        homeViewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
        return homeViewModel
    }

    private fun configureRecycler() {
        rvMovies.layoutManager = GridLayoutManager(this, 2)
        movieAdapter = MovieAdapter(homeViewModel.getBaseImageUrl())
        movieAdapter.setListener(this)
        rvMovies.adapter = movieAdapter
    }

    private fun init() {
        configureRecycler()
        homeViewModel.getMoviesResult().observe(this, Observer {
            when (it) {
                is Resource.Loading -> {
                    rvMovies.visibility = View.GONE
                    btnRetry.visibility = View.GONE
                    progress.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progress.visibility = View.GONE
                    btnRetry.visibility = View.GONE
                    rvMovies.visibility = View.VISIBLE
                    movieAdapter.updateMovies(it.data)
                }

                is Resource.Failure -> {
                    rvMovies.visibility = View.GONE
                    progress.visibility = View.GONE
                    btnRetry.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun events() {
        btnRetry.setOnClickListener { homeViewModel.fetchPopularMovies() }
    }

    override fun onMovieSelected(movie: MovieEntity) {
        homeViewModel.selectMovie(movie.id)
        val intent = Intent(this, MovieDetailActivity::class.java)
        startActivity(intent)
    }

}
