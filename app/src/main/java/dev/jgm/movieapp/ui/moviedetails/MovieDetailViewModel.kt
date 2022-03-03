package dev.jgm.movieapp.ui.moviedetails

import android.util.Log
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

    fun loadMovies(movieId: Int, language: String) {
        viewModelScope.launch {
            isLoading.value = true
            Log.wtf("VM", "Loading: TRUE")
            val response = getMovieVideos(movieId, language)
            when (response) {
                is Response.Failure -> {
                    Log.wtf("VM_FAILURE", "Loading: FALSE")
                    Log.wtf("VM_FAILURE", ". \n error: ${response.exception.message}")
                    isLoading.value = false
                }
                is Response.Loading -> isLoading.value = true
                is Response.Success -> {
                    Log.wtf(
                        "VM_SUCCESS",
                        ".\n name video: ${if (!response.data.isNullOrEmpty()) response.data[0].key else ""}"
                    )
                    Log.wtf("VM", "Loading: FALSE")
                    videos.value = response.data!!
                }
            }
        }
    }
}