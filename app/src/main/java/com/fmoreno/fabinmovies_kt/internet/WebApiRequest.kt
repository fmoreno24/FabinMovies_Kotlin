package com.fmoreno.fabinmovies_kt.internet

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.util.HashMap
import com.fmoreno.fabinmovies_kt.internet.WebServicesConstant.Constant.API_KEY
import com.fmoreno.fabinmovies_kt.internet.WebServicesConstant.Constant.BASE_URL_APPLICATION
import com.fmoreno.fabinmovies_kt.internet.WebServicesConstant.Constant.BASE_URL_DETAIL_MOVIE
import com.fmoreno.fabinmovies_kt.internet.WebServicesConstant.Constant.MOVIE
import com.fmoreno.fabinmovies_kt.internet.WebServicesConstant.Constant.POPULAR
import com.fmoreno.fabinmovies_kt.internet.WebServicesConstant.Constant.TOP_RATED
import com.fmoreno.fabinmovies_kt.model.DetailMovie
import com.fmoreno.fabinmovies_kt.model.Movie
import com.fmoreno.fabinmovies_kt.model.MovieResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class WebApiRequest(responseInterface: ResponseInterface, context: Context) {
    val mContext = context
    val gson = Gson()

    private var responseInterface: ResponseInterface = responseInterface
    var requestQueue = Volley.newRequestQueue(mContext)

    fun getMoviesPopular(pageNumber: Int){
        //constructing api url
        val ws_url: String = BASE_URL_APPLICATION + MOVIE + POPULAR +
                "?api_key=" + API_KEY.toString() + "&language=es-ES&page=" + pageNumber

        val stringReq = StringRequest(
            Request.Method.POST, ws_url,
            Response.Listener<String> { response ->
                var strResp = response.toString()
                responseInterface.getMoviesPopular(toListMovie(response))
                Log.d("getMoviesEndPoints", strResp);
            },
            Response.ErrorListener {
                responseInterface.getMoviesFail()
                Log.e("ErrorListener",it.toString())})

        requestQueue?.add(stringReq)
    }

    fun getMoviesTopRated(pageNumber: Int){
        //constructing api url
        val ws_url = BASE_URL_APPLICATION + MOVIE + TOP_RATED +
                "?api_key=" + API_KEY + "&language=es-ES&page=" + pageNumber

        val stringReq = StringRequest(
            Request.Method.POST, ws_url,
            Response.Listener<String> { response ->
                var strResp = response.toString()
                responseInterface.getMoviesTopRated(toListMovie(response))
                Log.d("getMoviesEndPoints", strResp);
            },
            Response.ErrorListener {
                responseInterface.getMoviesFail()
                Log.e("ErrorListener",it.toString())})

        requestQueue?.add(stringReq)
    }

    fun getMovieDetails(movieId: String){
        //constructing api url
        val ws_url = BASE_URL_APPLICATION + MOVIE + movieId +
                "?api_key=" + API_KEY + BASE_URL_DETAIL_MOVIE

        val stringReq = StringRequest(
            Request.Method.GET, ws_url,
            Response.Listener<String> { response ->
                var strResp = response.toString()
                responseInterface.getMovieDetails(toMovieDetails(response))
                Log.d("getMoviesEndPoints", strResp);
            },
            Response.ErrorListener {
                responseInterface.getMoviesFail()
                Log.e("ErrorListener",it.toString())})

        requestQueue?.add(stringReq)
    }

    /**
     * Metodo para convertir el listado de peliculas
     */
    fun toMovieDetails(string: String?): DetailMovie {
        return gson.fromJson<DetailMovie>(
            string,
            object : TypeToken<DetailMovie?>() {}.type
        )
    }

    fun toListMovie(string: String?): MovieResponse {
        return gson.fromJson<MovieResponse>(
            string,
            object : TypeToken<MovieResponse?>() {}.type
        )
    }
}