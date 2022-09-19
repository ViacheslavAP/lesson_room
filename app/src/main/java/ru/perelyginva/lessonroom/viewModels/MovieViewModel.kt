package ru.perelyginva.lessonroom.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.perelyginva.lessonroom.models.MovieModel
import ru.perelyginva.lessonroom.repositories.MovieRepository

class MovieViewModel(private val movieRepository: MovieRepository): ViewModel() {

    val products = movieRepository.products

    fun getFilter(nameCategory: String, priceProduct: String): LiveData<List<MovieModel>>{
       return movieRepository.getFilter(nameCategory, priceProduct)
    }

    fun startInsertProduct(nameProduct:String, categoryProduct: String, priceProduct: String){
        insertProduct(MovieModel(0, nameProduct, categoryProduct, priceProduct))
    }

    fun startUpdateProduct(
        idProduct: Int,
        nameProduct: String,
        categoryProduct: String,
        priceProduct: String){
        updateProduct(MovieModel(idProduct, nameProduct,
            categoryProduct, priceProduct))
    }

    fun insertProduct(movieModel: MovieModel) = viewModelScope.launch {
        movieRepository.insertProduct(movieModel)
    }

    fun updateProduct(movieModel: MovieModel) = viewModelScope.launch {
        movieRepository.updateProduct(movieModel)
    }

    fun deleteProduct(movieModel: MovieModel) = viewModelScope.launch {
        movieRepository.deleteProduct(movieModel)
    }

    fun deleteAllProduct() = viewModelScope.launch {
        movieRepository.deleteAllProduct()
    }
}