package ru.perelyginva.lessonroom.repositories

import androidx.lifecycle.LiveData
import ru.perelyginva.lessonroom.data.MovieDao
import ru.perelyginva.lessonroom.models.MovieModel

class MovieRepository(private val movieDao: MovieDao) {

    val products = movieDao.getAllProducts()

    fun getFilter(nameCategory: String, priceProduct: String):
            LiveData<List<MovieModel>> {

        return movieDao.getFilter(nameCategory, priceProduct)
    }

    suspend fun insertProduct(movieModel: MovieModel){
        movieDao.insertProduct(movieModel)
    }

    suspend fun updateProduct(movieModel: MovieModel){
        movieDao.updateProduct(movieModel)
    }

    suspend fun deleteProduct(movieModel: MovieModel){
        movieDao.deleteProduct(movieModel)
    }

    suspend fun deleteAllProduct(){
        movieDao.deleteAllProducts()
    }
}