package com.fmoreno.fabinmovies_kt.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.fmoreno.fabinmovies_kt.R
import com.fmoreno.fabinmovies_kt.model.Movie
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity: AppCompatActivity() {
    var movie: Movie? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        movie = intent.getSerializableExtra("movie") as Movie?
        setImage()
    }

    private fun setImage() {
        Glide.with(this)
            .load("http://image.tmdb.org/t/p/w500" + movie?.poster_path) //.load(moviesList.get(position).getPosterPath())
            .into(imageViewPoster)
        Glide.with(this)
            .load("http://image.tmdb.org/t/p/w500" + movie?.backdrop_path) //.load(moviesList.get(position).getPosterPath())
            .into(imageViewBanner)
    }
}