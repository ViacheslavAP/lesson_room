package ru.perelyginva.lessonroom.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.perelyginva.lessonroom.data.db.models.MovieModel
import ru.perelyginva.lessonroom.data.repositories.MovieRepository
import ru.perelyginva.lessonroom.domain.useCase.MoviesUseCase

class MovieViewModel(private val moviesUseCase: MoviesUseCase): ViewModel() {

    val products = moviesUseCase.loadMovies()

    fun getFilter(nameCategory: String, priceProduct: String): LiveData<List<MovieModel>>{
       return moviesUseCase.getFilter(nameCategory, priceProduct)
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
        moviesUseCase.insertProduct(movieModel)
    }

    fun updateProduct(movieModel: MovieModel) = viewModelScope.launch {
        moviesUseCase.updateProduct(movieModel)
    }

    fun deleteProduct(movieModel: MovieModel) = viewModelScope.launch {
        moviesUseCase.deleteProduct(movieModel)
    }

    fun deleteAllProduct() = viewModelScope.launch {
        moviesUseCase.deleteAllProduct()
    }
}