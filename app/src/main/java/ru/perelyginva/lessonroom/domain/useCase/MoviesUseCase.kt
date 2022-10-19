package ru.perelyginva.lessonroom.domain.useCase

import androidx.lifecycle.LiveData
import ru.perelyginva.lessonroom.data.db.models.MovieModel
import ru.perelyginva.lessonroom.domain.repository.MoviesCall

class MoviesUseCase(private val moviesCall: MoviesCall) {

    fun loadMovies(): LiveData<List<MovieModel>> {

        return moviesCall.loadMovies()
    }

    fun getFilter(nameCategory: String, priceProduct: String):
            LiveData<List<MovieModel>> {

        return moviesCall.getFilter(nameCategory, priceProduct)
    }

    suspend fun insertProduct(movieModel: MovieModel) {
        moviesCall.insertProduct(movieModel)
    }

    suspend fun updateProduct(movieModel: MovieModel) {
        moviesCall.updateProduct(movieModel)
    }

    suspend fun deleteProduct(movieModel: MovieModel) {
        moviesCall.deleteProduct(movieModel)
    }

    suspend fun deleteAllProduct() {
        moviesCall.deleteAllProduct()
    }
}