package dev.jgm.movieapp.ui.movielist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import dev.jgm.movieapp.databinding.ItemMovieListBinding
import dev.jgm.movieapp.domain.model.Movie
import dev.jgm.movieapp.utils.extension.loadImage

class MovieListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemMovieListBinding.bind(view)

    fun render(movie: Movie, onClick: (Movie) -> Unit) {
        binding.image.loadImage(movie.getImageUrl(movie.posterPath))
        binding.name.text = movie.title
        binding.root.setOnClickListener { onClick(movie) }
    }
}