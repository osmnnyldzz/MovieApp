package com.kotlin.movieapp.model

data class MovieData (
    val originalName: String? = null,
    val genreIDS: List<Long>? = null,
    val name: String? = null,
    val popularity: Double? = null,
    val originCountry: List<String>? = null,
    val voteCount: Long? = null,
    val firstAirDate: String? = null,
    val backdropPath: String? = null,
    val originalLanguage: String? = null,
    val id: Long? = null,
    val voteAverage: Double? = null,
    val overview: String? = null,
    val posterPath: String? = null
)

