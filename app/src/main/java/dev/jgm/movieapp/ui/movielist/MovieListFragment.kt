package dev.jgm.movieapp.ui.movielist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.jgm.movieapp.R
import dev.jgm.movieapp.databinding.FragmentMovieListBinding
import dev.jgm.movieapp.domain.model.Movie
import dev.jgm.movieapp.domain.model.MovieType
import java.util.*

@AndroidEntryPoint
class MovieListFragment :  Fragment(R.layout.fragment_movie_list) {

    private lateinit var binding: FragmentMovieListBinding
    private val viewModel: MovieListViewModel by viewModels()
    private lateinit var adapter: MovieListAdapter
    private val language = Locale.getDefault().language

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieListBinding.bind(view)
        setUpRecycler()
        setUpFilter()
        initObservers()
    }

    private fun setUpFilter() {
        binding.rbMostPopular.setOnClickListener { loadMovies(MovieType.POPULAR) }
        binding.rbPlayingNow.setOnClickListener { loadMovies(MovieType.PLAYING_NOW) }
    }

    private fun setUpRecycler() {
        adapter = MovieListAdapter { openDetailsMovie(it) }
        val layoutManager = GridLayoutManager(binding.root.context, 3)
        binding.rvMovieList.layoutManager = layoutManager
        binding.rvMovieList.adapter = adapter
    }

    private fun initObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) showLoading()
        }
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            if (movies.isNotEmpty()) {
                adapter.list = movies
                binding.rbPlayingNow.isEnabled = true
                binding.rbMostPopular.isEnabled = true
                binding.rvMovieList.visibility = View.VISIBLE
                binding.loading.root.visibility = View.GONE
                binding.message.root.visibility = View.GONE
            } else {
                setUpMessage(true)
            }
        }
        viewModel.error.observe(viewLifecycleOwner) { isError ->
            if (isError) {
                setUpMessage(isError)
            }
        }
    }

    private fun loadMovies(movieType: MovieType) {
        viewModel.loadMovies(movieType, language, 1)
    }

    private fun openDetailsMovie(movie: Movie) {
        val direction = MovieListFragmentDirections.goToDetails(movie)
        findNavController().navigate(direction)
    }

    private fun setUpMessage(isError: Boolean) {
        binding.message.title.text = getString(R.string.no_movie_found)
        binding.message.image.setImageResource(R.drawable.ic_no_movie_found)
        binding.message.button.text = getString(R.string.try_again)
        binding.message.button.visibility = View.GONE
        if (isError) {
            binding.message.body.text = getString(R.string.make_sure_have_Internet_connection)
            binding.message.body.visibility = View.VISIBLE
        } else {
            binding.message.body.visibility = View.GONE

        }

        binding.rbPlayingNow.isEnabled = true
        binding.rbMostPopular.isEnabled = true
        binding.loading.root.visibility = View.GONE
        binding.rvMovieList.visibility = View.GONE
        binding.message.root.visibility = View.VISIBLE
    }

    private fun showLoading() {
        binding.rbPlayingNow.isEnabled = false
        binding.rbMostPopular.isEnabled = false
        binding.rvMovieList.visibility = View.GONE
        binding.message.root.visibility = View.GONE
        binding.loading.root.visibility = View.VISIBLE
    }
}
