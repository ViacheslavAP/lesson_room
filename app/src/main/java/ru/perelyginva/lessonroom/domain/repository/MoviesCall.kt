package ru.perelyginva.lessonroom.domain.repository

import androidx.lifecycle.LiveData
import ru.perelyginva.lessonroom.data.db.models.MovieModel

interface MoviesCall {

     fun loadMovies(): LiveData<List<MovieModel>>

    fun getFilter(nameCategory: String, priceProduct: String): LiveData<List<MovieModel>>

    suspend fun insertProduct(movieModel: MovieModel)

    suspend fun updateProduct(movieModel: MovieModel)

    suspend fun deleteProduct(movieModel: MovieModel)
    suspend fun deleteAllProduct()
}