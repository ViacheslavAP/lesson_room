package ru.perelyginva.lessonroom.data.repositories

import androidx.lifecycle.LiveData
import ru.perelyginva.lessonroom.data.db.MovieDao
import ru.perelyginva.lessonroom.data.db.models.MovieModel
import ru.perelyginva.lessonroom.domain.repository.MoviesCall

class MovieRepository(private val movieDao: MovieDao): MoviesCall {

     override fun loadMovies(): LiveData<List<MovieModel>> {

        return movieDao.getAllProducts()
    }

    override fun getFilter(nameCategory: String, priceProduct: String):
            LiveData<List<MovieModel>> {

        return movieDao.getFilter(nameCategory, priceProduct)
    }

    override suspend fun insertProduct(movieModel: MovieModel) {
        movieDao.insertProduct(movieModel)
    }

    override suspend fun updateProduct(movieModel: MovieModel) {
        movieDao.updateProduct(movieModel)
    }

    override suspend fun deleteProduct(movieModel: MovieModel) {
        movieDao.deleteProduct(movieModel)
    }

   override suspend fun deleteAllProduct() {
        movieDao.deleteAllProducts()
    }
}