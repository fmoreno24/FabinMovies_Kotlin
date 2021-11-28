package com.fmoreno.fabinmovies_kt.adapter

import android.content.res.Resources
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fmoreno.fabinmovies_kt.R
import com.fmoreno.fabinmovies_kt.model.Movie
import com.fmoreno.fabinmovies_kt.utils.ImageLoadingListener

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var post: ImageView? = itemView.findViewById(R.id.imgView_post)

    fun bindLaunch(movie: Movie) {
        val resources = itemView.resources
        setMoviePoster(movie)
        //bindNameAndDate(movie, resources)
    }

    private fun setMoviePoster(movie: Movie) {
        val progressView = itemView.findViewById<View>(R.id.moviePosterProgress).apply { visibility = View.VISIBLE }
        val loadingListener = ImageLoadingListener(progressView)
        val posterPath = "http://image.tmdb.org/t/p/w500" + movie.poster_path

        Glide.with(itemView)
            .load(posterPath)
            .error(R.drawable.ic_launcher_foreground)
            .fallback(R.drawable.ic_launcher_foreground)
            .listener(loadingListener)
            .into(itemView.findViewById(R.id.imgView_post))
    }

    private fun bindNameAndDate(movie: Movie, resources: Resources) {
        //itemView.findViewById<TextView>(R.id.title).text = movie.title
    }
}