package dev.jgm.movieapp.ui.moviedetails

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.jgm.movieapp.R
import dev.jgm.movieapp.databinding.FragmentMovieDetailBinding
import dev.jgm.movieapp.domain.model.Movie
import dev.jgm.movieapp.domain.model.Video
import dev.jgm.movieapp.utils.extension.loadImage
import java.util.*

@AndroidEntryPoint
class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var binding: FragmentMovieDetailBinding
    private val args: MovieDetailFragmentArgs by navArgs()
    private val viewModel: MovieDetailViewModel by viewModels()
    private val locale = Locale.getDefault()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailBinding.bind(view)
        setUpToolbar()
        args.movie?.let { movie ->
            setUpMovieDetail(movie)
            viewModel.loadMovies(movie.id, locale.language)
        }
        initObservers()
    }

    private fun setUpMovieDetail(movie: Movie) {
        binding.collapsingToolbar.title = movie.title
        binding.title.text = movie.title
        binding.backdrop.loadImage(movie.getImageUrl(movie.backdropPath))
        binding.voteAverageBar.progress = movie.voteAverage.toInt()
        binding.overview.text = movie.overview
        binding.releaseDate.text = getString(R.string.release_date, movie.getDate(locale))
        binding.voteAverage.text = getString(R.string.vote_average, movie.voteAverage.toString())
        binding.genres.text = movie.getGenres(binding.root.context)
    }

    private fun setUpToolbar() {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> findNavController().popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) showLoading()
        }
        viewModel.videos.observe(viewLifecycleOwner) { videos ->
            if (!videos.isNullOrEmpty()) setUpVideos(videos) else setUpMessage(false)
        }
        viewModel.error.observe(viewLifecycleOwner) { isError ->
            if (isError) setUpMessage(isError)
        }
    }

    private fun setUpVideos(videos: List<Video>) {
        val adapter = MovieVideoAdapter(lifecycle, videos)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvVideos.layoutManager = layoutManager
        binding.rvVideos.adapter = adapter
        binding.message.root.visibility = View.GONE
        binding.loading.root.visibility = View.GONE
        binding.rvVideos.visibility = View.VISIBLE
    }

    private fun setUpMessage(isError: Boolean) {
        binding.message.title.text = getString(R.string.no_videos_found)
        binding.message.image.visibility = View.GONE
        binding.message.button.text = getString(R.string.try_again)
        binding.message.button.setOnClickListener {
            viewModel.loadMovies(args.movie!!.id, "en-US")
        }
        if (isError) {
            binding.message.body.text = getString(R.string.no_internet_connection)
            binding.message.body.visibility = View.VISIBLE
        } else {
            binding.message.body.visibility = View.GONE
        }

        binding.loading.root.visibility = View.GONE
        binding.rvVideos.visibility = View.GONE
        binding.message.root.visibility = View.VISIBLE
    }

    private fun showLoading() {
        binding.rvVideos.visibility = View.GONE
        binding.message.root.visibility = View.GONE
        binding.loading.root.visibility = View.VISIBLE
    }
}