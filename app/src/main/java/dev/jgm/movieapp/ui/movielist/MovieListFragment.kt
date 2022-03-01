package dev.jgm.movieapp.ui.movielist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import dev.jgm.movieapp.R
import dev.jgm.movieapp.databinding.FragmentMovieListBinding

class MovieListFragment :  Fragment(R.layout.fragment_movie_list) {

    private lateinit var binding: FragmentMovieListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieListBinding.bind(view)
        binding.text.setOnClickListener {
            findNavController().navigate(R.id.go_to_details)
        }
    }
}
