package com.kotlin.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kotlin.movieapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val overview = intent.getStringExtra("overview")
        val imageURL = intent.getStringExtra("imageURL")
        val releaseDate = intent.getStringExtra("releaseDate")
        val title = intent.getStringExtra("title")

        movie_detail_overview.text = overview
        movie_detail_release_date.text = releaseDate
        movie_detail_title.text = title
        Picasso.get().load("https://image.tmdb.org/t/p/original" + imageURL)
            .into(movie_detail_image)

    }
}