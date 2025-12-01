package com.example.test_lab_week_12.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test_lab_week_12.api.MovieService
import com.example.test_lab_week_12.model.Movie

class MovieRepository(private val movieService: MovieService) {

    // API KEY TMDB (v3 auth)
    private val apiKey = "YOUR API KEY"

    // LiveData that contains a list of movies
    private val movieLiveData = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = movieLiveData

    // LiveData that contains an error message
    private val errorLiveData = MutableLiveData<String>()
    val error: LiveData<String>
        get() = errorLiveData


    // fetch movies from the API
    suspend fun fetchMovies() {
        try {
            // get the list of popular movies from the API
            val popularMovies = movieService.getPopularMovies(apiKey)

            // update success result
            movieLiveData.postValue(popularMovies.results)

        } catch (exception: Exception) {

            // update error LiveData
            errorLiveData.postValue(
                "An error occurred: ${exception.message}"
            )
        }
    }
}
