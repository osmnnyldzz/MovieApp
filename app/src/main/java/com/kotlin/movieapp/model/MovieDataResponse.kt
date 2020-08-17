package com.kotlin.movieapp.model

import com.google.gson.annotations.SerializedName

data class MovieDataResponse(
    @SerializedName("page") val page : Int,
    @SerializedName("total_results") val total_results : Int,
    @SerializedName("total_pages") val total_pages : Int,
    @SerializedName("results") val results : ArrayList<MovieData>
)