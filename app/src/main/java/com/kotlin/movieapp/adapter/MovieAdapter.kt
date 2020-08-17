package com.kotlin.movieapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.movieapp.R
import com.kotlin.movieapp.model.MovieData
import com.kotlin.movieapp.model.MovieDataResponse
import com.kotlin.movieapp.ui.MovieDetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movies_list_item.view.*

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    lateinit var context: Context
    lateinit var rv : View
    private var list: List<MovieData>

    constructor(list: List<MovieData>) : super() {
        this.list = list
    }


    //ViewHolderin başlatılması için
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_list_item, parent, false)

        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = list[position]
        holder.itemView.data_movie_name.text = movie.title
        holder.itemView.data_movie_rate.text = movie.vote_average.toString()
        holder.itemView.data_movie_year.text = movie.release_date
        Picasso.get().load("https://image.tmdb.org/t/p/w440_and_h660_face" + movie.poster_path)
            .into(holder.itemView.data_movie_poster)

        holder.itemView.frameLayoutId.setOnClickListener {
            val context = holder.itemView.context

            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("overview", movie.overview)
            intent.putExtra("imageURL", movie.poster_path)
            intent.putExtra("releaseDate", movie.release_date)
            intent.putExtra("title", movie.title)
            context.startActivity(intent)
        }
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}


}