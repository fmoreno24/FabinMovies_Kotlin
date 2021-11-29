package com.fmoreno.fabinmovies_kt.model

import java.io.Serializable

class Movie: Serializable {
    var adult = false
    var backdrop_path: String? = null
    var genre_ids: List<Int>? = null
    var id = 0
    var original_language: String? = null
    var original_title: String? = null
    var overview: String? = null
    var popularity = 0.0
    var poster_path: String? = null
    var release_date: String? = null
    var title: String? = null
    var video = false
    var vote_average = 0.0
    var vote_count = 0

    fun getLikes(): String? {
        return if (vote_count == 1) {
            "1 Like"
        } else if (vote_count > 1) {
            vote_count.toString() + " Likes"
        } else {
            "No votes"
        }
    }

    fun getStars(): String? {
        return if (vote_average != null && vote_average > 0) {
            vote_average.toString() + " / 10"
        } else {
            "No Reviews"
        }
    }
}