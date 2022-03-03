package dev.jgm.movieapp.ui.moviedetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jgm.movieapp.core.Response
import dev.jgm.movieapp.domain.model.Video
import dev.jgm.movieapp.domain.usecases.GetMovieVideos
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieVideos: GetMovieVideos
) : ViewModel() {

    val videos = MutableLiveData<List<Video>>(emptyList())
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData(false)

    fun loadMovies(movieId: Int, language: String) {
        viewModelScope.launch {
            isLoading.value = true
            error.value = false

            val response = getMovieVideos(movieId, language)
            when (response) {
                is Response.Loading -> isLoading.value = true
                is Response.Success -> {
                    videos.value = response.data!!
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