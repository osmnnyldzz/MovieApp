package com.kotlin.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.movieapp.adapter.MovieAdapter
import com.kotlin.movieapp.api.MovieService
import com.kotlin.movieapp.api.RetrofitClient
import com.kotlin.movieapp.model.MovieData
import com.kotlin.movieapp.model.MovieDataResponse
import kotlinx.android.synthetic.main.activity_movie.*
import retrofit2.Call
import retrofit2.Response

const val API_KEY = "a127bbfea38f370fbbd9081a8d1e73fe"

class MovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        recyclerView.layoutManager=GridLayoutManager(this,2)

        RetrofitClient.getClient().create(MovieService::class.java)  // Interface'imiz ile burada retrofit nesnesi oluşturduk.
            .getMovieData(API_KEY,"tr-TR",1).enqueue(object :retrofit2.Callback<MovieDataResponse>{

                override fun onFailure(call: Call<MovieDataResponse>, t: Throwable) {
                    Toast.makeText(this@MovieActivity, "Failure", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<MovieDataResponse>, response: Response<MovieDataResponse>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            Log.d("Response", "Movies: ${responseBody.results}")
                            recyclerView.adapter = MovieAdapter(responseBody.results)
                        } else {
                            Log.d("Response", "Failed to get response")
                        }
                    }
                }

            })
    }
}