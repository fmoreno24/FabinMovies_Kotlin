package com.fmoreno.fabinmovies_kt.internet

class WebServicesConstant {
    object Constant {
        //Main Host Url to access apis
        val BASE_URL_APPLICATION = "https://api.themoviedb.org/3/"

        //Image urls
        val BASE_URL_IMAGE_ORIGINAL = "https://image.tmdb.org/t/p/original"
        val BASE_URL_IMAGE_W_500 = "https://image.tmdb.org/t/p/w500"

        //Details Movie
        val BASE_URL_DETAIL_MOVIE = "&append_to_response=videos,credits,reviews"

        val YOUTUBE_WEB_URL = "https://www.youtube.com/watch?v="

        val API_KEY = "3f4ccf9c8108bb8d03e86f9123add311"


        //api connections
        val MOVIE = "movie/"
        val TOP_RATED = "top_rated"
        val POPULAR = "popular"
    }

}