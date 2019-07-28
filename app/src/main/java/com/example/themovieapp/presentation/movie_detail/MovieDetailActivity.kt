package com.example.themovieapp.presentation.movie_detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.themovieapp.R
import com.example.themovieapp.data.entity.MovieDetailEntity
import com.example.themovieapp.data.entity.Resource
import com.example.themovieapp.presentation.adapter.TextAdapter
import com.example.themovieapp.presentation.base.BaseActivity
import com.example.themovieapp.presentation.movie_video.MovieVideoActivity
import com.example.themovieapp.utils.Constants
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.android.synthetic.main.activity_movie_detail.*
import javax.inject.Inject


class MovieDetailActivity : BaseActivity<MovieDetailViewModel>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var movieDetailViewModel: MovieDetailViewModel

    private lateinit var genreAdapter: TextAdapter
    private lateinit var companiesAdapter: TextAdapter
    private lateinit var countriesAdapter: TextAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        init()
        events()
    }

    override fun getViewModel(): MovieDetailViewModel {
        movieDetailViewModel = ViewModelProviders.of(this, factory).get(MovieDetailViewModel::class.java)
        return movieDetailViewModel
    }

    private fun configureGenreRecycler() {
        val layoutManager = FlexboxLayoutManager(this)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        rvGenres.layoutManager = layoutManager
        genreAdapter = TextAdapter()
        rvGenres.adapter = genreAdapter
    }

    private fun configureCompaniesRecycler() {
        val layoutManager = FlexboxLayoutManager(this)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        rvProductionCompanies.layoutManager = layoutManager
        companiesAdapter = TextAdapter()
        rvProductionCompanies.adapter = companiesAdapter
    }

    private fun configureCountriesRecycler() {
        val layoutManager = FlexboxLayoutManager(this)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        rvProductionCountries.layoutManager = layoutManager
        countriesAdapter = TextAdapter()
        rvProductionCountries.adapter = countriesAdapter
    }

    private fun init() {
        configureGenreRecycler()
        configureCompaniesRecycler()
        configureCountriesRecycler()
        movieDetailViewModel.getMoviesResult().observe(this, Observer {
            when (it) {
                is Resource.Loading -> {
                    nsvContent.visibility = View.GONE
                    btnRetry.visibility = View.GONE
                    progress.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    btnRetry.visibility = View.GONE
                    progress.visibility = View.GONE
                    nsvContent.visibility = View.VISIBLE
                    showMovieDetail(it.data.movieDetail)
                }
                is Resource.Failure -> {
                    nsvContent.visibility = View.GONE
                    btnRetry.visibility = View.GONE
                    progress.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun events() {
        btnRetry.setOnClickListener { movieDetailViewModel.fetchMovieDetail() }
        llPlayTrailer.setOnClickListener {
            val videoKey = movieDetailViewModel.getVideos()?.get(0)?.key
            showVideo(videoKey)
        }
    }

    private fun showVideo(videoKey: String?) {
        if (videoKey != null) {
            val intent = Intent(this, MovieVideoActivity::class.java)
            intent.putExtra(MovieVideoActivity.VIDEO_KEY, videoKey)
            startActivity(intent)
        }
    }

    private fun showMovieDetail(movieDetail: MovieDetailEntity) {
        val baseImageUrl = movieDetailViewModel.getBaseImageUrl()
        Glide.with(this)
            .load("$baseImageUrl${Constants.POSTER_SIZE}${movieDetail.poster_path}")
            .into(ivMovie)
        tvTitle.text = movieDetail.title
        tvOverview.text = movieDetail.overview
        tvRealeaseDate.text = movieDetail.release_date
        genreAdapter.updateTexts(movieDetail.genres.map { it.name })
        companiesAdapter.updateTexts(movieDetail.production_companies.map { it.name })
        countriesAdapter.updateTexts(movieDetail.production_countries.map { it.name })

    }

}