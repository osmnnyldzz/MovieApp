package com.kotlin.movieapp.model

data class MovieDataResponse(
    val page: Long? = null,
    val totalResults: Long? = null,
    val totalPages: Long? = null,
    val results: List<MovieData>? = null
)