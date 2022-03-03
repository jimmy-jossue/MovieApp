package dev.jgm.movieapp.ui.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jgm.movieapp.core.Response
import dev.jgm.movieapp.domain.model.Movie
import dev.jgm.movieapp.domain.model.MovieType
import dev.jgm.movieapp.domain.usecases.GetAllMovies
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getAllMovies: GetAllMovies
) : ViewModel() {

    val movies = MutableLiveData<List<Movie>>(emptyList())
    val isLoading = MutableLiveData<Boolean>()

    init {
        loadMovies(MovieType.POPULAR, "es", 1)
    }

    fun loadMovies(movieType: MovieType, language: String, page: Int) {
        viewModelScope.launch {
            isLoading.value = true

            val response = getAllMovies(movieType, language, page)
            when (response) {
                is Response.Failure -> {
                    isLoading.value = false
                }
                is Response.Loading -> isLoading.value = true
                is Response.Success -> {
                    if (!response.data.isNullOrEmpty()) {
                        movies.value = response.data!!
                    }
                }
            }
        }
    }

}