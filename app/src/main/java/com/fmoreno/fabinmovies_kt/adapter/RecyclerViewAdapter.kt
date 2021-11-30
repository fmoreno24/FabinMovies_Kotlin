package com.fmoreno.fabinmovies_kt.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.fmoreno.fabinmovies_kt.R
import com.fmoreno.fabinmovies_kt.model.Movie
import java.util.ArrayList

class RecyclerViewAdapter(oItemClickListener: OnItemClickListener) : RecyclerView.Adapter<MovieViewHolder>(), Filterable  {
    //private val mMovies: MutableList<Movie> = movies
    var mMovies: MutableList<Movie> = listOf<Movie>().toMutableList()
    var mFilteredMoviesList: MutableList<Movie> = listOf<Movie>().toMutableList()

    private val onItemClickListener: OnItemClickListener? = oItemClickListener
    var mFilter: MovieFilter? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view: View = LayoutInflater.from(
            parent.context
        ).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val startAnimation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.slide_up)

        val movie: Movie = mMovies.get(position)
        holder.post?.setOnClickListener { onItemClickListener!!.onItemClick(movie, holder.post!!) }
        holder.bindLaunch(mMovies[position])
        holder.itemView.startAnimation(startAnimation)
    }

    override fun getItemCount(): Int {
        return mMovies.size
    }

    override fun getFilter(): Filter? {
        if (mFilter == null) {
            mFilteredMoviesList.clear()
            mFilteredMoviesList.addAll(
                this.mMovies
            )
            mFilter = MovieFilter(this, this.mFilteredMoviesList)
        }
        return mFilter
    }

    fun addMovies(movies: MutableList<Movie>) {
        mMovies.addAll(movies)
        notifyDataSetChanged()
    }
}