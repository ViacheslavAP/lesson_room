package ru.perelyginva.lessonroom.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.perelyginva.lessonroom.data.db.models.CategoryModel
import ru.perelyginva.lessonroom.data.repositories.CategoryRepository
import ru.perelyginva.lessonroom.domain.useCase.CategoriesUseCase

class CategoryViewModel(private val categoryUseCase: CategoriesUseCase) : ViewModel() {

    val categories = categoryUseCase.loadCategories()

    fun startInsertCategory(nameCategory: String) {
        insertCategory(CategoryModel(0, nameCategory))
    }

    fun startUpdateCategory(idCategory: Int, nameCategory: String) {
        updateCategory(CategoryModel(idCategory, nameCategory))
    }

    fun insertCategory(categoryModel: CategoryModel) = viewModelScope.launch {
        categoryUseCase.insertCategory(categoryModel)
    }

    fun updateCategory(categoryModel: CategoryModel) = viewModelScope.launch {
        categoryUseCase.updateCategory(categoryModel)
    }

    fun deleteCategory(categoryModel: CategoryModel) = viewModelScope.launch {
        categoryUseCase.deleteCategory(categoryModel)
    }

    fun deleteAllCategory() = viewModelScope.launch {
        categoryUseCase.deleteAllCategory()
    }
}