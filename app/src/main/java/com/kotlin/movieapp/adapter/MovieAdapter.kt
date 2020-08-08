package com.kotlin.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.movieapp.R
import com.kotlin.movieapp.model.MovieData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movies_list_item.view.*

class MovieAdapter(private var moviesList: List<MovieData>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    //ViewHolderin başlatılması için
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_list_item, parent, false)

        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = moviesList.size

    //Verileri göstermek için
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.itemView.data_movie_name.text = movie.original_title
        holder.itemView.data_movie_rate.text = movie.vote_average.toString()

        Picasso.get().load("https://image.tmdb.org/t/p/w220_and_h330_face"+ movie.poster_path).into(holder.itemView.data_movie_poster)

    }


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}