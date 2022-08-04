package com.andry.moviesapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.andry.moviesapp.R
import com.andry.moviesapp.data.MovieData
import com.andry.moviesapp.databinding.ItemMovieBinding

val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieData>() {
    override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData): Boolean =
        oldItem.title == newItem.title
}
class MoviesAdapter : ListAdapter<MovieData, MoviesViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder =
        MoviesViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context))
        )

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        getItem(position).also {
            holder.binding.movieTitle.text = it.title
            holder.binding.movieYear.text = it.year
            holder.binding.genre.text = it.genre
            Glide.with(holder.binding.root)
                .load(it.poster)
                .error(R.drawable.ic_poster_error)
                .centerCrop()
                .into(holder.binding.posterBackground)
        }
    }
}

class MoviesViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)