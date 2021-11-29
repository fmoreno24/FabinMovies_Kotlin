package com.fmoreno.fabinmovies_kt.internet

import com.fmoreno.fabinmovies_kt.model.DetailMovie
import com.fmoreno.fabinmovies_kt.model.MovieResponse

interface ResponseInterface {
    fun getMoviesPopular(movies: MovieResponse?)
    fun getMoviesTopRated(movies: MovieResponse?)
    fun getMoviesFail()
    fun getMovieDetails(movie: DetailMovie)
}