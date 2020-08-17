package com.kotlin.movieapp.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.movieapp.R
import com.kotlin.movieapp.adapter.MovieAdapter
import com.kotlin.movieapp.api.MovieService
import com.kotlin.movieapp.api.RetrofitClient
import com.kotlin.movieapp.model.MovieData
import com.kotlin.movieapp.model.MovieDataResponse
import retrofit2.Call
import retrofit2.Response
import java.util.ArrayList

const val API_KEY = "a127bbfea38f370fbbd9081a8d1e73fe"


class MovieActivity : AppCompatActivity() {

    private lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerView : RecyclerView
    lateinit var progressBar: ProgressBar
    var list: MutableList<MovieData> = mutableListOf()
    lateinit var adapter: MovieAdapter
    var visibleItemCount: Int = 0
    var pastVisibleItemCount: Int = 0
    var totalItemCount = 0
    var loading:Boolean = false
    private var page:Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        progressBar = findViewById(R.id.progress_circular)
        recyclerView = findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        retrofitClient(page)
    }


    private fun retrofitClient(page: Int) {
        progressBar.visibility = View.VISIBLE
        RetrofitClient.getClient().create(MovieService::class.java)
            .getMovieData(API_KEY, "en-US", page)
            .enqueue(object : retrofit2.Callback<MovieDataResponse> {

                override fun onFailure(call: Call<MovieDataResponse>, t: Throwable) {
                    Toast.makeText(this@MovieActivity, "Failure", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<MovieDataResponse>, response: Response<MovieDataResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            progressBar.visibility = View.GONE
                            loading = true
                            setUpAdapter(responseBody.results)
                        } else {
                            progressBar.visibility = View.VISIBLE
                        }
                    }
                }

            })
    }

    private fun setUpAdapter(results: ArrayList<MovieData>) {
        if(list.size ==0){
            list = results
            adapter = MovieAdapter(list)
            recyclerView.adapter = adapter
        }
        else{
            var currrentPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
            list.addAll(results)
            adapter.notifyDataSetChanged()
            recyclerView.scrollToPosition(currrentPosition)
        }
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if (dy>0){
                    visibleItemCount = layoutManager.childCount
                    totalItemCount = layoutManager.itemCount
                    pastVisibleItemCount = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (loading){
                        if (visibleItemCount + pastVisibleItemCount >= totalItemCount){
                            loading = false
                            page++
                            retrofitClient(page)
                        }
                    }
                }

                super.onScrolled(recyclerView, dx, dy)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }
}