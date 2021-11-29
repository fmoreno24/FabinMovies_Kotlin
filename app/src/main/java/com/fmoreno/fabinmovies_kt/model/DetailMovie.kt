package com.fmoreno.fabinmovies_kt.model

class DetailMovie {
    var adult = false
    var backdrop_path: String? = null
    var belongs_to_collection: BelongsToCollection? = null
    var budget = 0
    var genres: List<Genre>? = null
    var homepage: String? = null
    var id = 0
    var imdb_id: String? = null
    var original_language: String? = null
    var original_title: String? = null
    var overview: String? = null
    var popularity = 0.0
    var poster_path: String? = null
    var production_companies: List<ProductionCompany>? = null
    var production_countries: List<ProductionCountry>? = null
    var release_date: String? = null
    var revenue = 0
    var runtime = 0
    var spoken_languages: List<SpokenLanguage>? = null
    var status: String? = null
    var tagline: String? = null
    var title: String? = null
    var video = false
    var vote_average = 0.0
    var vote_count = 0
    var videos: Videos? = null

    class BelongsToCollection {
        var id = 0
        var name: String? = null
        var poster_path: String? = null
        var backdrop_path: String? = null
    }

    class Genre {
        var id = 0
        var name: String? = null
    }

    class ProductionCompany {
        var id = 0
        var logo_path: String? = null
        var name: String? = null
        var origin_country: String? = null
    }

    class ProductionCountry {
        var iso_3166_1: String? = null
        var name: String? = null
    }

    class SpokenLanguage {
        var english_name: String? = null
        var iso_639_1: String? = null
        var name: String? = null
    }

    class Video {
        var iso_639_1: String? = null
        var iso_3166_1: String? = null
        var name: String? = null
        var key: String? = null
        var site: String? = null
        var size = 0
        var type: String? = null
        var official = false
        var published_at: String? = null
        var id: String? = null
    }

    class Videos {
        var results: List<Video>? = null
    }
}