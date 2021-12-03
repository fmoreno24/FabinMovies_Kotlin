package com.fmoreno.fabinmovies_kt.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.fmoreno.fabinmovies_kt.R
import com.fmoreno.fabinmovies_kt.adapter.TrailersAdapter
import com.fmoreno.fabinmovies_kt.internet.ResponseInterface
import com.fmoreno.fabinmovies_kt.internet.WebApiRequest
import com.fmoreno.fabinmovies_kt.model.DetailMovie
import com.fmoreno.fabinmovies_kt.model.Movie
import com.fmoreno.fabinmovies_kt.model.MovieResponse
import com.fmoreno.fabinmovies_kt.utils.DateUtils
import com.fmoreno.fabinmovies_kt.utils.ImageLoadingListener
import kotlinx.android.synthetic.main.activity_detail_movie.*
import java.util.ArrayList

class DetailMovieActivity: AppCompatActivity(), ResponseInterface {
    var mMovie: Movie? = null
    private var endpoints: WebApiRequest? = null

    private var progressBar: ProgressBar? = null


    var adapter: TrailersAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        mMovie = intent.getSerializableExtra("movie") as Movie?
        endpoints = WebApiRequest(this, this)

        sVideoList = ArrayList<DetailMovie.Video>()
        adapter = TrailersAdapter(sVideoList)
        rvTrailers.adapter = adapter
        rvTrailers.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        progressBar = ProgressBar(this, null, android.R.attr.progressBarStyleLarge)
        val params = RelativeLayout.LayoutParams(100, 100)
        params.addRule(RelativeLayout.CENTER_IN_PARENT)
        progressBar?.setVisibility(View.GONE)
        clMovieDetail.addView(progressBar, params)
        setImage()
        getNeworkMovieDetails()
    }

    /**
     * Metodo para consultar los datos de las peliculas
     */
    fun getNeworkMovieDetails(){
        try{
            endpoints?.requestQueue = Volley.newRequestQueue(this)
            endpoints?.getMovieDetails(mMovie?.id.toString())
        }catch (ex:Exception){
            Log.e("getNeworkMovies", ex.toString());
        }
    }

    private fun setImage() {
        val progressView = findViewById<View>(R.id.moviePosterProgress).apply { visibility = View.VISIBLE }
        val loadingListener = ImageLoadingListener(progressView)

        Glide.with(this)
            .load("http://image.tmdb.org/t/p/w500" + mMovie?.poster_path) //.load(moviesList.get(position).getPosterPath())
            .error(R.drawable.ic_launcher_foreground)
            .fallback(R.drawable.ic_launcher_foreground)
            .listener(loadingListener)
            .into(imageViewPoster)
        Glide.with(this)
            .load("http://image.tmdb.org/t/p/w500" + mMovie?.backdrop_path) //.load(moviesList.get(position).getPosterPath())
            .error(R.drawable.ic_launcher_foreground)
            .fallback(R.drawable.ic_launcher_foreground)
            .listener(loadingListener)
            .into(imageViewBanner)
    }

    override fun getMoviesPopular(movies: MovieResponse?) {

    }

    override fun getMoviesTopRated(movies: MovieResponse?) {

    }

    override fun getMoviesFail() {
        setTextOffline()
    }
    var sVideoList: MutableList<DetailMovie.Video> = listOf<DetailMovie.Video>().toMutableList()


    override fun getMovieDetails(movie: DetailMovie) {

        //Log.d("Detail", movieDetail.title);
        for (video in movie.videos?.results!!) {
            if (sVideoList?.contains(video) == false) {
                sVideoList?.add(video)
            }
        }
        setText(movie)
        setAnimation()
    }

    private fun setText(movie: DetailMovie) {
        textViewTitle.setText(mMovie?.title)
        textViewVotes.setText(mMovie?.getLikes())
        textViewStars.setText(mMovie?.getStars())
        textViewDate.setText(DateUtils.getYear(mMovie?.release_date))
        textViewDescription.setText(mMovie?.overview)
        if (movie != null) {
            textViewTagline.setText(movie.tagline)
            if (sVideoList != null && sVideoList.size > 0) {
                //adapter.notifyDataSetChanged();
                label_trailers.visibility = View.VISIBLE
                adapter?.addMovies(sVideoList)
            } else {
                label_trailers.visibility = View.GONE
            }
        }
    }

    private fun setTextOffline() {
        textViewTitle.setText(mMovie?.title)
        textViewVotes.setText(mMovie?.getLikes())
        textViewStars.setText(mMovie?.getStars())
        textViewDate.setText(DateUtils.getYear(mMovie?.release_date))
        textViewDescription.setText(mMovie?.overview)
        label_trailers.visibility = View.GONE
    }

    private fun setAnimation() {
        val atg = AnimationUtils.loadAnimation(this, R.anim.atg)
        val packageimg = AnimationUtils.loadAnimation(this, R.anim.packageimg)
        val right_in = AnimationUtils.loadAnimation(this, R.anim.right_in)
        val right_out = AnimationUtils.loadAnimation(this, R.anim.right_out)
        val slide_up = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        val slide_bottom = AnimationUtils.loadAnimation(this, R.anim.slide_bottom)
        val bounce = AnimationUtils.loadAnimation(this, R.anim.bounce)

        //imageViewPoster.startAnimation(atg);
        imageViewBanner.startAnimation(packageimg)
        textViewTitle.startAnimation(right_in)
        textViewVotes.startAnimation(slide_bottom)
        textViewStars.startAnimation(slide_bottom)
        textViewDate.startAnimation(slide_bottom)
        textViewTagline.startAnimation(slide_up)
        textViewDescription.startAnimation(slide_up)
    }
}