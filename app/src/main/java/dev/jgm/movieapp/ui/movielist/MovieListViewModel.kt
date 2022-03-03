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
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getAllMovies: GetAllMovies
) : ViewModel() {

    val movies = MutableLiveData<List<Movie>>(emptyList())
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData(false)

    init {
        loadMovies(MovieType.POPULAR, Locale.getDefault().language, 1)
    }

    fun loadMovies(movieType: MovieType, language: String, page: Int) {
        viewModelScope.launch {
            isLoading.value = true
            error.value = false

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