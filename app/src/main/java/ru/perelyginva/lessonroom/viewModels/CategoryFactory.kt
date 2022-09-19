package ru.perelyginva.lessonroom.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.perelyginva.lessonroom.repositories.CategoryRepository

class CategoryFactory(private val categoryRepository: CategoryRepository):
    ViewModelProvider.Factory  {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CategoryViewModel::class.java)){
            CategoryViewModel(this.categoryRepository) as T
        }else{
            throw IllegalArgumentException("ViewModel not Found")
        }
    }
}