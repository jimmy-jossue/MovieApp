package dev.jgm.movieapp.ui.moviedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import dev.jgm.movieapp.R
import dev.jgm.movieapp.domain.model.Video

class MovieVideoAdapter(
    private val lifecycle: Lifecycle,
    private val list: List<Video>
) : RecyclerView.Adapter<MovieVideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVideoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_video_list, parent, false)
        return MovieVideoViewHolder(lifecycle, view)
    }

    override fun onBindViewHolder(holder: MovieVideoViewHolder, position: Int) {
        val movie = list[position]
        if (movie.site == "YouTube") {
            holder.render(movie)
        }
    }

    override fun getItemCount(): Int = list.size
}