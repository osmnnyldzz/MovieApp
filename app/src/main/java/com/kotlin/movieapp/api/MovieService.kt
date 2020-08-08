package com.kotlin.movieapp.api

import com.kotlin.movieapp.model.MovieDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/*
* Bu requestin sonucunu göndermek için bir fonksiyon oluşturduk.
* Json tipi object olduğu için Call<MovieDataResponse> yazdık ve cast edeceğimiz yeri belirttik.
* Eğer Array olsaydı Call<List<MovieDataResponse>> kullanmamız gerekiyordu.
* */

interface MovieService {
    @GET("tv/popular")    //Base url'den sonra gelecek adresi yazdık. Sonra değişiklik gösterebilecek alanlar için Query kullandık.
    fun getMovieData(@Query("api_key") apiKey:String,@Query("language") language:String, @Query("page") page:Int): Call<MovieDataResponse>
}