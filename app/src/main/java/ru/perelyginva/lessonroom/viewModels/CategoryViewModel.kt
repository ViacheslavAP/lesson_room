package ru.perelyginva.lessonroom.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.perelyginva.lessonroom.models.CategoryModel
import ru.perelyginva.lessonroom.repositories.CategoryRepository

class CategoryViewModel(private val categoryRepository: CategoryRepository) : ViewModel() {

    val categories = categoryRepository.categories

    fun startInsertCategory(nameCategory: String) {
        insertCategory(CategoryModel(0, nameCategory))
    }

    fun startUpdateCategory(idCategory: Int, nameCategory: String) {
        updateCategory(CategoryModel(idCategory, nameCategory))
    }

    fun insertCategory(categoryModel: CategoryModel) = viewModelScope.launch {
        categoryRepository.insertCategory(categoryModel)
    }

    fun updateCategory(categoryModel: CategoryModel) = viewModelScope.launch {
        categoryRepository.updateCategory(categoryModel)
    }

    fun deleteCategory(categoryModel: CategoryModel) = viewModelScope.launch {
        categoryRepository.deleteCategory(categoryModel)
    }

    fun deleteAllCategory() = viewModelScope.launch {
        categoryRepository.deleteAllCategory()
    }
}