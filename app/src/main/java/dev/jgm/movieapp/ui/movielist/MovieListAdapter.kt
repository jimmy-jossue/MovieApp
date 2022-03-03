package dev.jgm.movieapp.ui.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.jgm.movieapp.R
import dev.jgm.movieapp.domain.model.Movie
import kotlin.properties.Delegates

class MovieListAdapter(
    private val onClick: (Movie) -> Unit = {}
) : RecyclerView.Adapter<MovieListViewHolder>() {

    var list: List<Movie> by Delegates.observable(emptyList()) { _, old, new ->
        DiffUtil.calculateDiff(
            object : DiffUtil.Callback() {
                override fun getOldListSize(): Int = old.size
                override fun getNewListSize(): Int = new.size

                override fun areItemsTheSame(oldItemPos: Int, newItemPos: Int): Boolean {
                    return (old[oldItemPos].id == new[newItemPos].id)
                }

                override fun areContentsTheSame(oldItemPos: Int, newItemPos: Int): Boolean {
                    return old[oldItemPos] == new[newItemPos]
                }
            }
        ).dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_movie_list, parent, false)
        return MovieListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val movie = list[position]
        holder.render(movie, onClick)
    }

    override fun getItemCount(): Int = list.size
}