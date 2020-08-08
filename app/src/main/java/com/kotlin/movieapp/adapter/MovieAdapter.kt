package com.kotlin.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.movieapp.R
import com.kotlin.movieapp.model.MovieData
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
        val datad = moviesList[position]
        holder.itemView.data_movie_name.text = datad.name
    }


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}