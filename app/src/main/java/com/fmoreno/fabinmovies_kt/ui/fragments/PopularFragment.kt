package com.fmoreno.fabinmovies_kt.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.Volley
import com.fmoreno.fabinmovies_kt.R
import com.fmoreno.fabinmovies_kt.adapter.OnItemClickListener
import com.fmoreno.fabinmovies_kt.adapter.RecyclerViewAdapter
import com.fmoreno.fabinmovies_kt.internet.ResponseInterface
import com.fmoreno.fabinmovies_kt.internet.WebApiRequest
import com.fmoreno.fabinmovies_kt.model.DetailMovie
import com.fmoreno.fabinmovies_kt.model.Movie
import com.fmoreno.fabinmovies_kt.model.MovieResponse
import com.fmoreno.fabinmovies_kt.ui.DetailMovieActivity
import com.google.gson.Gson
import java.util.ArrayList

class PopularFragment: Fragment(), ResponseInterface, OnItemClickListener {

    private var mRootView: View? = null

    var layoutManager: GridLayoutManager? = null


    //For Load more functionality
    private var previousTotal = 0
    private var loading = true
    private val visibleThreshold = 2
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    //current page number
    private var pageNumber = 1

    private var progressBar: ProgressBar? = null

    private var rlMoviesList: RelativeLayout? = null
    private var rv_popular: RecyclerView? = null

    private var include: View? = null

    private var gson: Gson? = null

    private var adapter: RecyclerViewAdapter? = null


    var mMovies: MutableList<Movie> = listOf<Movie>().toMutableList()
    var movies_all: MutableList<Movie> = listOf<Movie>().toMutableList()

    private var endpoints: WebApiRequest? = null

    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = inflater.inflate(R.layout.fragment_popular, container)
        rlMoviesList = mRootView?.findViewById(R.id.rlMoviesList)
        rv_popular = mRootView?.findViewById(R.id.rv_popular)
        initialObjects()
        initOperation()
        return mRootView
    }

    companion object {
        fun create(): PopularFragment {
            return PopularFragment()
        }
    }

    /**
     * Fabian Moreno
     * Inicializar los objetos
     */
    fun initialObjects() {
        try{
            endpoints = WebApiRequest(this, this.requireContext())
            mMovies = ArrayList<Movie>()
            movies_all = ArrayList<Movie>()
            gson = Gson()
        }catch (ex:Exception){
            Log.e("initialObjects", ex.toString());
        }
    }

    /**
     * Fabian Moreno
     * Inicializador de views y consulta de datos
     */
    private fun initOperation(){
        try{
            adapter = RecyclerViewAdapter(this)
            progressBar = ProgressBar(this.requireActivity(), null, android.R.attr.progressBarStyleLarge)

            val params = RelativeLayout.LayoutParams(100, 100)
            params.addRule(RelativeLayout.CENTER_IN_PARENT)
            progressBar!!.visibility = View.GONE

            rlMoviesList?.addView(progressBar, params)
            layoutManager = GridLayoutManager(activity, resources.getInteger(R.integer.span_count))
            rv_popular?.layoutManager = layoutManager
            rv_popular?.setHasFixedSize(true)
            rv_popular?.clearOnScrollListeners() //clear scrolllisteners


            rv_popular?.setAdapter(adapter)

            getNeworkMovies()

            rv_popular!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0) {
                        visibleItemCount = recyclerView.childCount
                        totalItemCount = layoutManager!!.itemCount
                        firstVisibleItem = layoutManager!!.findFirstVisibleItemPosition()
                        if (loading) {
                            if (totalItemCount > previousTotal) {
                                loading = false
                                previousTotal = totalItemCount
                            }
                        }
                        if (!loading && totalItemCount - visibleItemCount
                            <= firstVisibleItem + visibleThreshold
                        ) {
                            // End has been reached
                            Log.i("InfiniteScrollListener", "End reached")
                            getNeworkMovies()
                            loading = true
                        }
                    }
                }
            })
        }catch (ex:Exception){
            Log.e("initOperation", ex.toString());
        }
    }


    /**
     * Metodo para consultar los datos de las peliculas
     */
    fun getNeworkMovies(){
        try{
            endpoints?.requestQueue = Volley.newRequestQueue(this.requireActivity())
            endpoints?.getMoviesPopular(pageNumber)
        }catch (ex:Exception){
            Log.e("getNeworkMovies", ex.toString());
        }
    }

    override fun getMoviesPopular(movies: MovieResponse?) {
        try{
            pageNumber++
            adapter?.addMovies(movies?.results as MutableList<Movie>)

            Log.d("getMovies", movies.toString())
        }catch (ex:Exception){
            Log.e("getMovies", ex.toString());
        }
    }

    override fun getMoviesTopRated(movies: MovieResponse?) {
        TODO("Not yet implemented")
    }

    override fun getMoviesFail() {
        TODO("Not yet implemented")
    }

    override fun getMovieDetails(movie: DetailMovie) {
        TODO("Not yet implemented")
    }

    /**
     * Actualización de objeto
     */
    fun addMoviesAll(movies: List<Movie>){
        try {
            //this.movies_all.clear()
            this.movies_all.addAll(movies)
        } catch (e: Exception) {
            Log.e("addMoviesAll", e.toString());
        }
    }

    /**
     * Actualización de objeto y vista con el adapter
     */
    private fun updateList(movies: List<Movie>) {
        try{
            this.mMovies.clear()
            this.mMovies.addAll(movies)
            adapter?.notifyDataSetChanged()
        }catch (ex:Exception){
            Log.e("updateList", ex.toString());
        }

    }

    var movieDetail: Movie? = null
    override fun onItemClick(movie: Movie?, mView: View) {
        movieDetail = movie
        val datailActivity = Intent(context, DetailMovieActivity::class.java)
        datailActivity.putExtra("movie", movieDetail)

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this.requireActivity(), mView, "poster"
        )

        startActivity(datailActivity, options.toBundle())
        this.requireActivity().overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out)
    }
}