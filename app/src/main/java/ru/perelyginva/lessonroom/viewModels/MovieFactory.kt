package ru.perelyginva.lessonroom.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.perelyginva.lessonroom.repositories.MovieRepository

class MovieFactory(private val movieRepository: MovieRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MovieViewModel::class.java)){
            MovieViewModel(this.movieRepository) as T
        }else{
            throw IllegalArgumentException("ViewModel not Found")
        }
    }
}