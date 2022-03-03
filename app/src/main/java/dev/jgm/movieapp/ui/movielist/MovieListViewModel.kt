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
    val isLoading = MutableLiveData(true)
    val error = MutableLiveData(false)

    fun loadMovies(movieType: MovieType, language: String, page: Int) {
        viewModelScope.launch {
            error.value = false
            isLoading.value = true

            val response = getAllMovies(movieType, language, page)
            when (response) {
                is Response.Loading -> isLoading.value = true
                is Response.Success -> {
                    movies.value = response.data!!
                    isLoading.value = false
                }
                is Response.Failure -> {
                    error.value = true
                    isLoading.value = false
                }
            }
        }
    }

}