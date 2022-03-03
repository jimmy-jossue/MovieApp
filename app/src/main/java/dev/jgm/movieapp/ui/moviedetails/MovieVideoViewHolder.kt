package dev.jgm.movieapp.ui.moviedetails

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dev.jgm.movieapp.databinding.ItemVideosBinding
import dev.jgm.movieapp.domain.model.Video

class MovieVideoViewHolder(
    private val lifecycle: Lifecycle,
    view: View
) : RecyclerView.ViewHolder(view) {

    private val binding = ItemVideosBinding.bind(view)

    fun render(video: Video) {
        binding.name.text = video.name
        lifecycle.addObserver(binding.playerVideo)
        binding.playerVideo.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(video.key, 0f)
            }
        })
    }
}
