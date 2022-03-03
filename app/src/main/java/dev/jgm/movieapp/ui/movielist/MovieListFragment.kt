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
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            if (movies.isNotEmpty()) {
                adapter.list = movies
                binding.loading.root.visibility = View.GONE
            } else {

            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.loading.root.visibility = View.VISIBLE
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
}
